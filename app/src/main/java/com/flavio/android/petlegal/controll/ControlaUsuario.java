package com.flavio.android.petlegal.controll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.flavio.android.petlegal.dao.Dao;
import com.flavio.android.petlegal.model.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Flávio on 26/05/2018.
 */

public class ControlaUsuario {
    private Context context;
    private static final String tabela = "usuario";
    Dao banco ;

    public ControlaUsuario( Context context){
        this.context = context;
        banco = new Dao ( this.context );
        criaTabelaUsuario ();
    }
    /**CREATE Tabela metodo
     * tabela: Cria a tabela*/
    public boolean criaTabelaUsuario(){
        String campos = " 'idPessoa' INTEGER, 'tipo' INTEGER, 'cpf' INTEGER, 'senha' TEXT, 'status' INTEGER ";
        return banco.criarTabela ( tabela, campos);
    }/**FIM CREATE Tabela*/


    /**READ Tabela metodos
     * consulta: Consulta um registro no banco
     * lista: consulta e retorna multiplos registros do banco
     */
    public Usuario consultarUsuario(Usuario userEnt){
        String query = "SELECT * FROM "+tabela+" WHERE cpf = "+userEnt.getCpf ()+" ;";
        Usuario user = new Usuario (  );
        try {
            Cursor cursor = banco.consulta ( query );
                    user.setIdPessoa ( cursor.getInt ( 0 ) );   //'idPessoa' INTEGER
                    user.setTipo ( cursor.getInt ( 1 ) );       //'tipo' INTEGER
                    user.setCpf ( cursor.getInt ( 2 ) );        //'cpf' INTEGER
                    user.setSenha ( cursor.getString ( 3 ) );   //'senha' TEXT
                    user.setStatus ( cursor.getInt ( 4 ) );     //'status' INTEGER
            return user;
        }catch(Exception e){
            return user;
        }


    }
    public Usuario consultraUsuarioById(int id){
        String query = "SELECT * FROM "+tabela+" WHERE idPessoa = "+id+" ;";
        Usuario user = new Usuario (  );
        try {
            Cursor cursor = banco.consulta ( query );
            user.setIdPessoa ( cursor.getInt ( 0 ) );   //'idPessoa' INTEGER
            user.setTipo ( cursor.getInt ( 1 ) );       //'tipo' INTEGER
            user.setCpf ( cursor.getInt ( 2 ) );        //'cpf' INTEGER
            user.setSenha ( cursor.getString ( 3 ) );   //'senha' TEXT
            user.setStatus ( cursor.getInt ( 4 ) );     //'status' INTEGER
            return user;
        }catch(Exception e){
            return user;
        }


    }

  /*   public List<Usuario> listarUsuarios() {
     List<Usuario> usuarios = new ArrayList<Usuario> ();
     String selectQuery = "SELECT * FROM "+tabela;
     //adicionar pesquisa
     Cursor cursor;
         try {
             cursor = banco .listaTeste ( selectQuery );
             Usuario usu = new Usuario ();
             if (cursor.moveToFirst ())
                 do {
                     usu.setIdPessoa (  cursor.getInt ( 0 ));
                     usu.setCpf ( cursor.getInt ( 1 ) );
                     usu.setSenha ( cursor.getString ( 2 ) );
                     usu.setTipo ( cursor.getInt ( 3 ) );
                     usu.setStatus ( cursor.getInt( 4 ) );
                     usuarios.add ( usu );
                 } while (cursor.moveToNext ());
             return usuarios;
         } catch (Exception e) {
             return usuarios;
         }
     }*/

    public List<Usuario> listaUsuarios(){
        List<Usuario> usuarios = new ArrayList<Usuario> (  );
        String selectQuery = "SELECT * FROM usuario";
        Cursor cursor ;
        try {
            cursor = banco.returnConexao ().getWritableDatabase ().rawQuery ( selectQuery, null );
            if (cursor.moveToFirst ())
                do {
                    Usuario user = new Usuario ();
                    user.setIdPessoa ( cursor.getInt ( 0 ) );   //'idPessoa' INTEGER
                    user.setTipo ( cursor.getInt ( 1 ) );       //'tipo' INTEGER
                    user.setCpf ( cursor.getInt ( 2 ) );        //'cpf' INTEGER
                    user.setSenha ( cursor.getString ( 3 ) );   //'senha' TEXT
                    user.setStatus ( cursor.getInt ( 4 ) );     //'status' INTEGER
                    usuarios.add ( user);
                } while (cursor.moveToNext ());
            return usuarios;
        }catch (Exception e){
            return usuarios;
        }
    }/**FIM READ*/


    /**UPDATE Tabela metodos
     *inserirUsuario: Prepara dados e envia ao DAO para inserir NOVO registro no banco
     *atualizarUsuario: Prepara dados e envia ao DAO para atualizar registro EXISTENTE no banco
     *desativarUsuario: Prepara os dados e envia ao DAO para desativar um Usuario*/
    public boolean inserirUsuario(Usuario user){
        ContentValues cv = getCv ( user );
            return  banco.inserir ( tabela,cv );
    }
    public boolean atualizarUsuario(Usuario user){
        String where = "idPessoa = "+ user.getIdPessoa ()+" ";
        ContentValues cv = getCv(user);
        return banco.atualiza ( tabela,cv,where );
    }
    public boolean desativarUsuario(Usuario user){
        String where = "idPessoa ='"+ user.getIdPessoa ()+"'";
        user.setStatus ( 0 );
        ContentValues cv = getCv(user);
        return banco.atualiza ( tabela,cv,where );
    }/**FIM UPDATE Tabela*/

    public boolean deletarUsuario(Usuario user){
        String where = "idPessoa = "+ user.getIdPessoa ()+" ";
        return banco.deletar ( tabela, where );
    }


   private ContentValues getCv(Usuario user){
       ContentValues cv = new ContentValues (  );
       cv.put("idPessoa",user.getIdPessoa ());
       cv.put("tipo",user.getTipo ());
       cv.put("cpf", user.getCpf ());
       cv.put("senha", user.getSenha ());
       cv.put("status", user.getStatus ());
       return cv;
   }

}
