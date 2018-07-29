package com.flavio.android.petlegal.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.flavio.android.petlegal.R;
import com.flavio.android.petlegal.controll.ControlaTelefone;
import com.flavio.android.petlegal.model.Telefone;

public class TesteMVCTelefone extends AppCompatActivity {
    Button btnConsultar, btnInserir,btnAtualizar,btnDeletar;
    ControlaTelefone ct;
    Telefone telefone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_teste_mvctelefone );

        btnConsultar = (Button)findViewById ( R.id.btnConsultarPetMVC ) ;
        btnInserir = (Button) findViewById ( R.id.btnInserirPetMVC );
        btnAtualizar =  (Button)findViewById ( R.id.btnAtualizarPetMVC );
        btnDeletar = (Button)findViewById ( R.id.btnDeletarPetMVC ) ;

        ct = new ControlaTelefone ( getApplicationContext () );

        telefone = new Telefone (-1,-1,1,58585858 );

        btnConsultar.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(ct.consultarTelefone ( telefone ).getNumeroTel () == telefone.getNumeroTel ()){
                    telefone = ct.consultarTelefone ( telefone );
                    sendMessage ( "Telefone consultado 58585858" );
                }else
                    sendMessage ( "Telefone 58585858 não existe" );
            }
        } );
        btnInserir.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(ct.consultarTelefone ( telefone ).getNumeroTel () != telefone.getNumeroTel ()) {
                    if (ct.inserirTelefone ( telefone )) {
                        sendMessage ( "Telefone adicionado 58585858" );
                    } else
                        sendMessage ( "Telefone não foi adicionado" );
                }else
                    sendMessage ( "Telefone 58585858 já existe" );
            }
        } );
        btnAtualizar.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Telefone atualizado = new Telefone(telefone.getIdTel (),-1,1,12121212);
                if(ct.atualizarTelefone ( atualizado )){
                    sendMessage ( "Telefone Atualizado 12121212" );
                }else
                    sendMessage ( "Náo existe o telefone 58585858" );
            }
        } );
        btnDeletar.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Telefone atualizado = new Telefone ( -1,-1,1,12121212 );
                if(ct.deletaTelefone ( telefone )){
                    sendMessage ( "Telefone 12121212 Deletado" );
                }else
                    sendMessage ( "Telefone 12121212 não existe" );
            }
        } );


    }
    public void sendMessage(String texto){
        Toast.makeText ( this, texto, Toast.LENGTH_SHORT ).show ();
    }
}
