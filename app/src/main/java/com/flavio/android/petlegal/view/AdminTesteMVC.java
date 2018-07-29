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

public class AdminTesteMVC extends AppCompatActivity {
    int cpf;
    private Button pessoa, usuario,telefone,pet,meuPerfil;
    private ControlaUsuario cu;
    private Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_admin_teste_mvc );

        pessoa = (Button)findViewById ( R.id.btnTabelaPessoa );
        usuario = (Button) findViewById ( R.id.btnTabelaUsuario );
        telefone = (Button)findViewById ( R.id.btnTabelaTelefone );
        pet = (Button)findViewById ( R.id.btnTabelaPet );
        meuPerfil = (Button)findViewById ( R.id.btnMeuPerfil );
        cu = new ControlaUsuario ( getApplicationContext () );

        Bundle bundle = getIntent ().getExtras ();
        cpf = bundle.getInt ( "cpf" );
        if(cpf==123456){
            meuPerfil.setVisibility ( View.INVISIBLE );
        }

        pessoa.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent it = new Intent ( AdminTesteMVC.this, TesteMVCPessoa.class );
                startActivity ( it );
            }
        } );
        usuario.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent it = new Intent ( AdminTesteMVC.this, TesteMVC.class );
                startActivity ( it );
            }
        } );
        telefone.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent it = new Intent ( AdminTesteMVC.this , TesteMVCTelefone.class );
                startActivity ( it );
            }
        } );
        pet.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent ( AdminTesteMVC.this, TesteMVCPet.class );
                startActivity ( intent );
            }
        } );
        meuPerfil.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(cpf==123456){
                    sendMessage ( "O Administrador padrão do sistema não possui Perfil" );
                }else{
                    user = new Usuario (  );
                    user.setCpf ( cpf );
                    user = cu.consultarUsuario ( user );
                    Intent intent = new Intent ( AdminTesteMVC.this, User.class );
                    intent.putExtra ( "id",user.getIdPessoa () );
                    startActivity ( intent );
                }
            }
        } );

    }
    public void sendMessage(String texto){
        Toast.makeText ( this, texto, Toast.LENGTH_SHORT ).show ();
    }
    public void onBackPressed() {
      Intent  intent = new Intent ( this, Inicio.class );
      startActivity ( intent );
    }
}
