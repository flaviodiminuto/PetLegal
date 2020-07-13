package com.flavio.android.petlegal.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.flavio.android.petlegal.R;
import com.flavio.android.petlegal.controll.ControlaPessoa;
import com.flavio.android.petlegal.controll.ControlaUsuario;
import com.flavio.android.petlegal.model.Usuario;
import com.flavio.android.petlegal.util.DoneOptionUtil;
import com.flavio.android.petlegal.util.MaskEditUtil;

public class Inicio extends AppCompatActivity {

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
        btnCadastro = findViewById ( R.id.cadastro_botao_registrar);
        btnLogin = findViewById ( R.id.btnLogin );
        vcpf =  findViewById ( R.id.cadastro_cpf);
        vsenha = findViewById ( R.id.cadastro_password);

        cu = new ControlaUsuario ( getApplicationContext () );
        cp = new ControlaPessoa ( getApplicationContext () );

        configurarCampoCPF();
        conrigurarBotaoLogin();
        configurarBotaoCadastro();
        DoneOptionUtil.configurarDone(this.vsenha,btnLogin);

    }

    private void configurarBotaoCadastro() {
        btnCadastro.setOnClickListener ( new View.OnClickListener (){
            @Override
            public void onClick(View view) {
                Intent it = new Intent ( Inicio.this, Cadastro.class );
                startActivity ( it );
            }
        });
    }

    private void conrigurarBotaoLogin() {
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
                        sendMessage ( "Usuário inativo !" ); //Usuario inativo
                } else
                    sendMessage ( "Usuario ou senha incorretos" );


            }
        } );
    }

    private void configurarCampoCPF() {
        this.vcpf.addTextChangedListener(MaskEditUtil.mask(this.vcpf,MaskEditUtil.FORMAT_CPF));
    }

    /**Aqui pegamos o conteúdo do txtCpf e mesmo que não tenha número trazemos um valor inteiro para a
     * variável para que não gere erro de execução pois a variavel cpf vai esperar um valor que não pode ser NULL
     */
    public Usuario sendUser(){
        Usuario user= new Usuario ();
        scpf = vcpf.getText ().toString ();
        //Preenche o campo cpf caso  o EditText não esteja vazio e seu conteúdo contenha números
        if(!scpf.isEmpty () && scpf.matches ( "[0-9]{11}" )) {
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

    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }
}
