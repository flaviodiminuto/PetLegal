package com.flavio.android.petlegal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Environment;

import com.flavio.android.petlegal.conectionfactory.ConectionFactory;


/**
 * Created by Flávio on 29/05/2018.
 */

public class Dao {
    private ConectionFactory conexao;
    private Context context;

    public Dao(Context context){
        this.context = context;
        conexao = new ConectionFactory (context );
    }
    /**CREATE Tabela*/
    public boolean criarTabela(String nomeTabela, String campos){
        String sql = "CREATE TABLE IF NOT EXISTS "+nomeTabela+" ("+campos+");";
        conexao.openDB ();
        try{
        conexao.getWritableDatabase ().execSQL ( sql );
            return true;
        }catch (Exception e){
            return false;
        }finally {
            conexao.close ();
        }
    }/**FIM CREATE*/

    /**READ Tabela*/
    public Cursor consulta(String selectQuery) {
        conexao.openDB ();
        Cursor cursor;
        try{
            cursor = conexao.getWritableDatabase ().rawQuery(selectQuery,null);
            cursor.moveToNext ();
                return cursor;
        }catch ( Exception e){
            return null;
        }
        finally {
            conexao.close ();
        }
    }

    public ConectionFactory returnConexao (){
        return this.conexao;
    }

    public Cursor listaTeste (String query){

        Cursor cursor;
        try {
            cursor = conexao.getWritableDatabase ().rawQuery ( query, null );
            return cursor;
        }catch (Exception e){
            cursor = null;
            return cursor;
        }

    }    /**UPDATE Campos*/
    public boolean inserir(String tabela, ContentValues cv){
        conexao.openDB ();
        try{
        if(conexao.getWritableDatabase().insert(tabela, null, cv)==0)
            return false;
        else
            return true;
        }catch (Exception e){
            e.printStackTrace (  );
            return false;
        }finally {
            conexao.close ();
        }
    }
    public boolean atualiza(String tabela, ContentValues cv,String where){
        conexao.openDB ();
        try{
        if(conexao.getWritableDatabase ().update(tabela,cv,where,null)==0)
            return false;
        else
            return true;
        }catch (Exception e){
            e.printStackTrace (  );
            return false;
        }finally {
            conexao.close ();
        }
    }/**FIM UPDATE*/




    /**DELETE Tabela */
    public boolean deletaTabela(String tabela ){
        conexao.openDB ();
        String deleteTable= "DROP TABLE IF EXISTS "+tabela;
        try {
            conexao.getWritableDatabase ().execSQL ( deleteTable);
            return true;
        }catch (Exception e){
            e.printStackTrace (  );
            return false;
        }finally {
            conexao.close ();
        }
    }

    /**DELETE Campos*/
    public boolean deletar(String tabela, String where){
        conexao.openDB ();
        try{
            if((conexao.getWritableDatabase ().delete ( tabela,where,null ))==0)
                return false;
            else
                return true;
        }catch (Exception e){
            return false;
        }finally {
            conexao.close ();
        }

    } /**FIM DELETE*/

}
