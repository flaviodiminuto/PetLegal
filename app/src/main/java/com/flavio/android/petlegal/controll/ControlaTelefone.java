package com.flavio.android.petlegal.controll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.flavio.android.petlegal.dao.Dao;
import com.flavio.android.petlegal.model.Telefone;
import com.flavio.android.petlegal.view.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Flávio
 * Aluno da Fatec Zona sul - São Paulo
 * on 07/06/2018.
 */

public class ControlaTelefone {
    private Context context;
    private static final String tabela = "telefone";
    Dao banco ;

    public ControlaTelefone( Context context){
        this.context = context;
        banco = new Dao ( this.context );
        criaTabelaTelefone ();
    }
    /**CREATE Tabela metodo
     * tabela: Cria a tabela*/


    public boolean criaTabelaTelefone(){
        String campos = " 'idTel' INTEGER PRIMARY KEY AUTOINCREMENT, 'idPessoa' INTEGER, 'tipoTel' INTEGER, 'numeroTel' INTEGER ";
        return banco.criarTabela ( tabela, campos);
    }/**FIM CREATE Tabela*/


    /**READ Tabela metodos
     * consulta: Consulta um registro no banco
     * lista: consulta e retorna multiplos registros do banco
     */

    public Telefone consultarTelefone(Telefone telefoneEnt){
        String query = "SELECT * FROM "+tabela+" WHERE numeroTel = "+telefoneEnt.getNumeroTel ()+" ;";
        Telefone telefone = new Telefone (  );
        try {
            Cursor cursor = banco.consulta ( query );
            if(cursor!=null) {
                telefone.setIdTel ( cursor.getInt ( 0 ) );
                telefone.setIdPessoa ( cursor.getInt ( 1 ) );
                telefone.setTipoTel ( cursor.getInt ( 2 ) );
                telefone.setNumeroTel ( cursor.getInt ( 3 ) );
            }
            return telefone;
        }catch(Exception e){
            return telefone;
        }
    }
    public List<Telefone> listaTelefones(int id){
        List<Telefone> telefones = new ArrayList<> (  );
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
                    Telefone telefone = new Telefone (  );
                    telefone.setIdTel ( cursor.getInt ( 0 ) );
                    telefone.setIdPessoa ( cursor.getInt ( 1 ) );
                    telefone.setTipoTel ( cursor.getInt ( 2 ) );
                    telefone.setNumeroTel ( cursor.getInt ( 3 ) );
                    telefones.add ( telefone );
                }while (cursor.moveToNext ());
            }
            return telefones;
        }catch(Exception e){
            return telefones;
        }
    }
    /**FIM READ*/


    /**UPDATE Tabela metodos
     *inserirUsuario: Prepara dados e envia ao DAO para inserir NOVO registro no banco
     *atualizarUsuario: Prepara dados e envia ao DAO para atualizar registro EXISTENTE no banco
     *desativarUsuario: Prepara os dados e envia ao DAO para desativar um Usuario*/


    public boolean inserirTelefone(Telefone telefone){
        ContentValues cv = new ContentValues ();
        try {
            // cv.put("id", pessoa.getId ());
            cv.put ( "idPessoa", telefone.getIdPessoa () );
            cv.put ( "tipoTel", telefone.getTipoTel () );
            cv.put ( "numeroTel", telefone.getNumeroTel () );
            return banco.inserir ( tabela, cv );
        }catch ( Exception e){
            return false;
        }

    }
    public boolean atualizarTelefone(Telefone telefone){
        String where = " idTel = "+ telefone.getIdTel ()+" ";
        ContentValues cv = getCv ( telefone );
        return banco.atualiza ( tabela,cv,where );
    }

    public boolean deletaTelefone(Telefone telefone){
        String where = "idTel = "+telefone.getIdTel ()+" ";
        return banco.deletar ( tabela, where );
    }

    private ContentValues getCv(Telefone telefone){
        ContentValues cv = new ContentValues (  );
        try {
            cv.put ( "idTel", telefone.getIdTel () );
            cv.put ( "idPessoa", telefone.getIdPessoa () );
            cv.put ( "tipoTel",telefone.getTipoTel () );
            cv.put ( "numeroTel", telefone.getNumeroTel () );
        }catch (Exception e){
            return cv;
        }
        return cv;
    }
}
