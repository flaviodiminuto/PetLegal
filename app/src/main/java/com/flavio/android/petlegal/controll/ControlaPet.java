package com.flavio.android.petlegal.controll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.flavio.android.petlegal.dao.Dao;
import com.flavio.android.petlegal.model.Pet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Flávio on 26/05/2018.
 */

public class ControlaPet {

    private static final String tabela = "pet";
    Dao banco ;
    Context context;

    public ControlaPet( Context context){
        this.context = context;
        banco = new Dao ( this.context );
        criaTabelaPet ();
    }

    public boolean criaTabelaPet(){
        String campos = " 'id' INTEGER PRIMARY KEY AUTOINCREMENT, 'idPessoa' INTEGER, 'peso' DOUBLE,'cor' TEXT,'nome' TEXT, 'raca' TEXT, 'porte' TXT,'caminhoFotoPet' TEXT ";
        return banco.criarTabela ( tabela, campos);
    }

    public Pet consultarPet(Pet petEnt){
        String query = "SELECT * FROM "+tabela+" WHERE idPessoa = "+petEnt.getIdPessoa ()+" AND nome = '"+petEnt.getNome () +"' ;";
        Pet pet = new Pet();
        try {
            Cursor cursor = banco.consulta ( query );
            if(cursor!=null) {
                pet.setId ( cursor.getInt ( 0 ) );
                pet.setIdPessoa ( cursor.getInt ( 1 ) );
                pet.setPeso ( cursor.getDouble ( 2 ) );
                pet.setCor ( cursor.getString ( 3 ) );
                pet.setNome ( cursor.getString ( 4 ) );
                pet.setRaca ( cursor.getString ( 5 ) );
                pet.setPorte ( cursor.getString ( 6 ) );
                pet.setCaminhoFotoPet ( cursor.getString ( 7 ) );
            }
            return pet;
        }catch(Exception e){
            return pet;
        }

    }
    public Pet returnPetById (int id) {
        String query = "SELECT * FROM " + tabela + " WHERE id = " + id + " ;";
        Pet pet = new Pet ();
        try {
            Cursor cursor = banco.consulta ( query );
            if (cursor != null) {
                pet.setId ( cursor.getInt ( 0 ) );
                pet.setIdPessoa ( cursor.getInt ( 1 ) );
                pet.setPeso ( cursor.getDouble ( 2 ) );
                pet.setCor ( cursor.getString ( 3 ) );
                pet.setNome ( cursor.getString ( 4 ) );
                pet.setRaca ( cursor.getString ( 5 ) );
                pet.setPorte ( cursor.getString ( 6 ) );
                pet.setCaminhoFotoPet ( cursor.getString ( 7 ) );
            }
            return pet;
        } catch (Exception e) {
            return pet;
        }
    }
    public List<Pet> listaPetsById(int id){
        List<Pet> pets = new ArrayList<> (  );
        String query;
        if(id == -2) {
            query = "SELECT * FROM " + tabela + " ;";
        }else {
            query = "SELECT * FROM " + tabela + " WHERE idPessoa = " + id + " ;";
        }

        Cursor cursor;
        try{
            cursor = banco.consulta ( query );
            if(cursor.moveToFirst ()) {
                do {
                    Pet pet = new Pet (  );
                    pet.setId ( cursor.getInt ( 0 ) );
                    pet.setIdPessoa ( cursor.getInt ( 1 ) );
                    pet.setPeso ( cursor.getDouble ( 2 ) );
                    pet.setCor ( cursor.getString ( 3 ) );
                    pet.setNome ( cursor.getString ( 4 ) );
                    pet.setRaca ( cursor.getString ( 5 ) );
                    pet.setPorte ( cursor.getString ( 6 ) );
                    pet.setCaminhoFotoPet ( cursor.getString ( 7 ) );
                    pets.add ( pet );
                }while (cursor.moveToNext ());
            }
            return pets;
        }catch(Exception e){
            return pets;
        }
    }
    public boolean inserirPet(Pet pet){
        ContentValues cv = getCv ( pet );
        if(banco.inserir ( tabela,cv ))
            return true;
        else
            return false;
    }
    public boolean atualizarPet(Pet pet){
        String where = "id = "+ pet.getId ()+" ";
        ContentValues cv = getCv ( pet );
        return banco.atualiza ( tabela,cv,where );
    }
    public boolean deletar(Pet pet){
        String where = "id = "+pet.getId ()+" ";
        return banco.deletar ( tabela, where );
    }

    private ContentValues getCv(Pet pet){
        ContentValues cv = new ContentValues (  );
        cv.put("idPessoa",pet.getIdPessoa ());
        cv.put("peso",pet.getPeso ());
        cv.put("cor",pet.getCor ());
        cv.put("nome",pet.getNome ());
        cv.put("raca", pet.getRaca ());
        cv.put("porte",pet.getPorte ());
        cv.put("caminhoFotoPet",pet.getCaminhoFotoPet ());
        return cv;
    }


}
