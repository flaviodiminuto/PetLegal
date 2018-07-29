package com.flavio.android.petlegal.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.flavio.android.petlegal.R;
import com.flavio.android.petlegal.controll.ControlaUsuario;
import com.flavio.android.petlegal.model.Usuario;

public class AdminTeste extends AppCompatActivity {
    EditText txtTabela;
    Button btnCriar,btnConsultar, btnAtualizar, btnInserir,btnDeletar;
    ControlaUsuario cu;
    Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_admin_teste );

        txtTabela = findViewById ( R.id.txtTabela );
        btnCriar = findViewById ( R.id.btnCriar );
        btnConsultar = findViewById ( R.id.btnConsultarUsuarioMVC );
        btnAtualizar = findViewById ( R.id.btnAtualizar );
        btnInserir = findViewById ( R.id.btnInserir );
        btnDeletar = findViewById ( R.id.btnDeletar );
      //  " 'idPessoa' INTEGER, 'tipo' INTEGER, 'cpf' INTEGER, 'senha' TEXT, 'status' INTEGER "
        user = new Usuario ( 1,1,123456,"admin2",1 );
        cu = new ControlaUsuario ( getApplicationContext ());

        btnCriar.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                sendMessage ( "CRIANDO TABELA "+txtTabela.getText ().toString () );
                if(criarTabela()){
                    sendMessage ( "TABELA "+txtTabela.getText ().toString ()+" criada" );
                    Usuario validado ;
                    validado = cu.consultarUsuario ( user );
                   if(validado.getSenha ().equals ( user.getSenha () )) {
                        sendMessage ( "Usuario " + user.getSenha () + " já EXISTE" );
                    }else{
                        if (cu.inserirUsuario (user)) {
                            sendMessage ( "Usuario " + user.getSenha () + " inserido" );
                        } else
                            sendMessage ( "Usuario " + user.getSenha () + " Não foi inserido" );
                    }
                }else
                    sendMessage ( "TABELA "+txtTabela.getText ().toString ()+" não foi criada" );

            }
        } );

        btnConsultar.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Usuario admin = new Usuario ( 0,1,123456,"admin",1 );
                Usuario consultado = cu.consultarUsuario ( user );
                if(admin.getSenha ().equals ( consultado.getSenha () )){
                    sendMessage ( "Usuario admin consultado" );
                }else
                    sendMessage ( "Usuario admin não existe" );
            }
        } );
        btnAtualizar.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Usuario atualizado = new Usuario ( 0,1,123456,"admin",1  );
                atualizado = cu.consultarUsuario ( atualizado );
                atualizado.setSenha ( "atualizado" );
                if(cu.atualizarUsuario ( atualizado )){
                    sendMessage ( "Usuario foi atualizado com sucesso! senha= "+atualizado.getSenha () );
                }else
                    sendMessage ( "Não foi possível atualizar Usuario!" );

            }
        } );

        btnInserir.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Usuario inserido = new Usuario ( 0,3,123456,"inserido",1 );
                if(cu.inserirUsuario ( inserido )){
                    sendMessage ( "Usuario "+inserido.getSenha ()+" inserido com sucesso!" );
                }else
                    sendMessage ( "Usuario "+inserido.getSenha ()+"não foi inserido" );
            }
        } );

        btnDeletar.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Usuario deletado = new Usuario ( 0,3,123456,"atualizado",1 );
                        deletado = cu.consultarUsuario ( deletado );
                    if(deletado!=null ){
                    if(cu.deletarUsuario ( deletado)){
                        sendMessage ( "Usuario "+deletado.getSenha ()+" deletado" );
                        }else
                            sendMessage ( "Usuario "+deletado.getSenha ()+ " não foi deletado" );
                    }

            }
        } );

    }
    private void sendMessage(String texto){
        Toast.makeText ( this,texto,Toast.LENGTH_SHORT ).show ();
    }
    private boolean criarTabela(){
        String tabela = txtTabela.getText ().toString ();
        if(!tabela.isEmpty ()){
            return cu.criaTabelaUsuario ( );
        }else
            return false;
    }
}
