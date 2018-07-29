package com.flavio.android.petlegal.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.flavio.android.petlegal.R;
import com.flavio.android.petlegal.controll.ControlaPet;
import com.flavio.android.petlegal.model.Pet;

public class TesteMVCPet extends AppCompatActivity {
    Button consultar, inserir, atualizar, deletar;
    ControlaPet cpet;
    Pet pet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_teste_mvcpet );

        cpet = new ControlaPet ( getApplicationContext () );
        pet = new Pet (-1, -1,12.6,"Branco","Pukie","Sem Vergonha","Pequeno","");

        consultar = (Button) findViewById ( R.id.btnConsultarPetMVC );
        inserir = (Button) findViewById ( R.id.btnInserirPetMVC);
        atualizar = (Button) findViewById ( R.id.btnAtualizarPetMVC);
        deletar = (Button) findViewById ( R.id.btnDeletarPetMVC);

        consultar.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Pet validado = cpet.consultarPet ( pet );
                if(validado.getNome ().equals ( pet.getNome () )){
                    pet = cpet.consultarPet ( pet );
                    sendMessage ( "PET Consultado" );}
                else
                    sendMessage ( "PET NÃO foi consultado" );
            }
        } );
        inserir.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(cpet.inserirPet ( pet )) {
                    pet = cpet.consultarPet ( pet );
                    sendMessage ( "Pet inserido" );
                }
                else
                    sendMessage ( "Pet NÃO foi inserido" );
            }
        } );
        atualizar.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                Pet atualizado = new Pet (pet.getId (), pet.getIdPessoa (),4.2,"Gold","Pukie","Super Cão","Master","");
                if(cpet.atualizarPet ( atualizado ))
                    sendMessage ( "Atualizado \nRaça: "+atualizado.getRaca () );
                else
                    sendMessage ( "PET NÂO foi atualizado" );
            }
        } );
        deletar.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(cpet.deletar ( pet ))
                    sendMessage ( "PET Deletado" );
                else
                    sendMessage ( "PET NÂO foi deletado" );
            }
        } );
    }

    public void sendMessage(String texto){
        Toast.makeText ( this, texto, Toast.LENGTH_SHORT ).show ();
    }
}
