package com.flavio.android.petlegal.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.flavio.android.petlegal.R;
import com.flavio.android.petlegal.controll.ControlaUsuario;
import com.flavio.android.petlegal.model.Usuario;

public class TesteMVC extends AppCompatActivity {
    Button btnConsultar, btnInserir,btnAtualizar,btnDeletar,listaUsuarios;
    ControlaUsuario cu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_teste_mvc );
        cu = new ControlaUsuario ( getApplicationContext () );
        btnConsultar = (Button)findViewById ( R.id.btnConsultarUsuarioMVC ) ;
        btnInserir = (Button) findViewById ( R.id.btnInserirUsuarioMVC );
        btnAtualizar =  (Button)findViewById ( R.id.btnAtualizarUsuarioMVC );
        btnDeletar = (Button)findViewById ( R.id.btnDeletarUsuarioMVC ) ;
        listaUsuarios = (Button)findViewById ( R.id.btnListaUsuariosAdmin );

        btnConsultar.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(consultarUsuarioMVC ()){
                    Toast.makeText ( TesteMVC.this, "Usuario teste consultado", Toast.LENGTH_SHORT ).show ();
                }else
                Toast.makeText ( TesteMVC.this, "Usuario não pôde ser consultado", Toast.LENGTH_SHORT ).show ();
            }
        } );
        btnInserir.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(inserirUsuarioMVC ()){
                    Toast.makeText ( TesteMVC.this, "Usuari 'Inserido' Adicionado com sucesso!", Toast.LENGTH_SHORT ).show ();
                }else
                    Toast.makeText ( TesteMVC.this, "USUARIO teste NÃO FOI INSERIDO", Toast.LENGTH_SHORT ).show ();
            }
        } );
        btnAtualizar.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(atualizarUsuarioMVC ()){
                    Toast.makeText ( TesteMVC.this, "Usuario Teste atualizado para Atualizado", Toast.LENGTH_SHORT ).show ();
                }else
                    Toast.makeText ( TesteMVC.this, "Usuario não pôde ser atualizado", Toast.LENGTH_SHORT ).show ();
            }
        } );
        //Somente Deleta se tiver um usuário com senha 'auterado'
        btnDeletar.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(deletarUsuarioMVC ())
                    Toast.makeText ( TesteMVC.this, "Usuario deletado com sucesso", Toast.LENGTH_SHORT ).show ();
                else
                    Toast.makeText ( TesteMVC.this, "Usuario NÃO pôde ser deletado", Toast.LENGTH_SHORT ).show ();
            }
        } );
        listaUsuarios.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent it = new Intent ( TesteMVC.this, ListaUsuarios.class     );
                startActivity ( it );
            }
        } );
    }

    private boolean consultarUsuarioMVC(){
        Usuario user = new Usuario(-1,3,999,"teste",1);
        if(user.getSenha ().equals (  cu.consultarUsuario ( user ).getSenha ()))
            return true;
        else
            return false;

    }
    private boolean inserirUsuarioMVC(){
        Usuario user = new Usuario ( -1,3,999,"teste",1 );
        if(!consultarUsuarioMVC ()) {
            if (cu.inserirUsuario ( user )) {
                return true;
            } else
                return false;
        } else
        {
            Toast.makeText ( this, "Usuario já existe", Toast.LENGTH_SHORT ).show ();
            return false;
        }
        }
    private boolean atualizarUsuarioMVC(){
        Usuario user = new Usuario(-1,3,999,"alterado",1);
        if(consultarUsuarioMVC ()) {
            if (cu.atualizarUsuario ( user ))
                return true;
            else
                return false;
        }else
        {
            Toast.makeText ( this, " Usuario não Existe para atualizar", Toast.LENGTH_SHORT ).show ();
            return false;
        }
    }
    //Somente Deleta se tiver um usuário com senha 'auterado'
    private boolean deletarUsuarioMVC(){
        Usuario user = new Usuario(-1,3,999,"alterado",1);
       if(user.getSenha ().equals (  cu.consultarUsuario ( user ).getSenha ())) {
           if (cu.deletarUsuario ( user ))
               return true;
           else
               return false;
       }else {
           Toast.makeText ( this, "Usuário 'alterado' já foi deletado ", Toast.LENGTH_SHORT ).show ();
           return false;
       }
    }

}
