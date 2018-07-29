package com.flavio.android.petlegal.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.flavio.android.petlegal.R;
import com.flavio.android.petlegal.controll.ControlaPessoa;
import com.flavio.android.petlegal.controll.ControlaUsuario;
import com.flavio.android.petlegal.model.Usuario;

public class Inicio extends AppCompatActivity {


    Usuario logando;
    int cpf;
    String scpf;
    EditText vcpf,vsenha; // cpf e senha do View
    Button btnCadastro;
    Button btnLogin;
    ControlaUsuario cu;
    ControlaPessoa cp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_inicio );
        btnCadastro = findViewById ( R.id.btnCadastrar );
        btnLogin = findViewById ( R.id.btnLogin );
        vcpf =  findViewById ( R.id.txtCpf );
        vsenha = findViewById ( R.id.txtPassword );
        cu = new ControlaUsuario ( getApplicationContext () );
        cp = new ControlaPessoa ( getApplicationContext () );

       criaAdmin();

        btnCadastro.setOnClickListener ( new View.OnClickListener (){
            @Override
            public void onClick(View view) {
                Intent it = new Intent ( Inicio.this, Cadastro.class );
                startActivity ( it );
            }
        });

        btnLogin.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Usuario validado = validaUser ( sendUser () ) ;
                    if (sendUser ().getSenha ().equals ( validado.getSenha () )) {
                        if (validado.getStatus ()==1) {
                            validado = validaUser ( validado );
                            if (validado.getTipo () == 1) {
                                Intent it = new Intent ( Inicio.this, AdminTesteMVC.class );
                                it.putExtra ( "cpf",validado.getCpf () );
                                startActivity ( it );
                            } else {
                                Intent it = new Intent ( Inicio.this, User.class );
                                it.putExtra ( "id",validado.getIdPessoa () );
                                startActivity ( it );
                            }
                        }else
                            sendMessage ( "Usuário inválido !" ); //Usuario desativado no sistema
                    } else
                        sendMessage ( "Usuario ou senha incorretos" );


            }
        } );

    }

    /**Aqui pegamos o conteúdo do txtCpf e mesmo que não tenha número trazemos um valor inteiro para a
     * variável para que não gere erro de execução pois a variavel cpf vai esperar um valor que não pode ser NULL
     */
    public Usuario sendUser(){
        Usuario user= new Usuario ();
        scpf = vcpf.getText ().toString ();
        //Preenche o campo cpf caso  o EditText não esteja vazio e seu conteúdo contenha números
        if(!scpf.isEmpty () && scpf.matches ( "[0-9]{" + scpf.length () + "}" )) {
            cpf = Integer.parseInt ( scpf );
        }else{
            cpf =-1;
        }
        String senha = vsenha.getText ().toString ();

        user.setCpf ( cpf );
        user.setSenha ( senha );

        return user;
    }
    public Usuario validaUser(Usuario user){
        ControlaUsuario cu = new ControlaUsuario ( getApplicationContext () );
        user = cu.consultarUsuario ( user );
        return user;
    }
    public void sendMessage(String texto){
        Toast.makeText ( this,texto,Toast.LENGTH_SHORT).show ();
    }

    /**O Campo a seguir cria as tabelas necessárias a aplicação e o usuário administrador caso ele ainda não exista
     * geralmente é realizado na primeira vez que a aplicação é executada no
     * dispositivo
     */
    public void criaAdmin(){

        if(cu.criaTabelaUsuario ()){
          //  Toast.makeText ( this,"Tabela USUARIO criada com sucesso!",Toast.LENGTH_SHORT).show ();
            Usuario user = new Usuario ( 123456,"admin",1);
            Usuario validado = cu.consultarUsuario ( user );

            if(validado.getCpf ()!=user.getCpf () )  {
                cu.inserirUsuario (user);
            //    Toast.makeText ( this, "Usuario admin criado", Toast.LENGTH_SHORT ).show ();}
            //else {
                //Toast.makeText ( this, "Usuario admin ja existe", Toast.LENGTH_SHORT ).show ();
            }

        }
    }
}
