package com.flavio.android.petlegal.controll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.flavio.android.petlegal.dao.Dao;
import com.flavio.android.petlegal.model.Pessoa;


/**
 * Created by Flávio on 26/05/2018.
 */

public class ControlaPessoa {

    private Context context;
    private static final String tabela = "pessoa";
    Dao banco ;

    public ControlaPessoa( Context context){
        this.context = context;
        banco = new Dao ( this.context );
        criaTabelaPessoa ();
    }
    /**CREATE Tabela metodo
     * tabela: Cria a tabela*/


    public boolean criaTabelaPessoa(){
        String campos = " 'id' INTEGER PRIMARY KEY AUTOINCREMENT, 'cpf' INTEGER, 'nome' TEXT,'sobrenome' TEXT,'email' TEXT, 'logradouro' TEXT, 'bairro' TXT, 'numero' INTEGER, 'complemento' TEXT,'cep' INTEGER, 'caminhoFotoPessoa' TEXT ";
        return banco.criarTabela ( tabela, campos);
    }/**FIM CREATE Tabela*/


    /**READ Tabela metodos
     * consulta: Consulta um registro no banco
     * lista: consulta e retorna multiplos registros do banco
     */
    /** @param id
     * @param cpf
     * @param nome
     * @param sobrenome
     * @param email
     * @param logradouro
     * @param bairro
     * @param numero
     * @param complemento
     * @param cep
     * @param caminhoFotoPessoa
     */
    public Pessoa consultarPessoa(Pessoa pessoaEnt){
        String query = "SELECT * FROM "+tabela+" WHERE cpf = "+pessoaEnt.getCpf ()+" ;";
        Pessoa pessoa = new Pessoa();
        try {
            Cursor cursor = banco.consulta ( query );
            if(cursor!=null) {
                pessoa.setId ( cursor.getInt ( 0 ) );
                pessoa.setCpf ( cursor.getInt ( 1 ) );
                pessoa.setNome ( cursor.getString ( 2 ) );
                pessoa.setSobrenome ( cursor.getString ( 3 ) );
                pessoa.setEmail ( cursor.getString ( 4 ) );
                pessoa.setLogradouro ( cursor.getString ( 5 ) );
                pessoa.setBairro ( cursor.getString ( 6 ) );
                pessoa.setNumero ( cursor.getInt ( 7 ) );
                pessoa.setComplemento ( cursor.getString ( 8 ) );
                pessoa.setCep ( cursor.getInt ( 9 ) );
                pessoa.setCaminhoFotoPessoa ( cursor.getString ( 10 ) );
            }
            return pessoa;
        }catch(Exception e){
            return pessoa;
        }

    }
    public Pessoa returnPessoaById (int id){
        String query = "SELECT * FROM "+tabela+" WHERE id = "+id+" ;";
        Pessoa pessoa = new Pessoa();
        try {
            Cursor cursor = banco.consulta ( query );
            if(cursor!=null) {
                pessoa.setId ( cursor.getInt ( 0 ) );
                pessoa.setCpf ( cursor.getInt ( 1 ) );
                pessoa.setNome ( cursor.getString ( 2 ) );
                pessoa.setSobrenome ( cursor.getString ( 3 ) );
                pessoa.setEmail ( cursor.getString ( 4 ) );
                pessoa.setLogradouro ( cursor.getString ( 5 ) );
                pessoa.setBairro ( cursor.getString ( 6 ) );
                pessoa.setNumero ( cursor.getInt ( 7 ) );
                pessoa.setComplemento ( cursor.getString ( 8 ) );
                pessoa.setCep ( cursor.getInt ( 9 ) );
                pessoa.setCaminhoFotoPessoa ( cursor.getString ( 10 ) );
            }
            return pessoa;
        }catch(Exception e){
            return pessoa;
        }
    }/**FIM READ*/




    /**UPDATE Tabela metodos
     *inserirUsuario: Prepara dados e envia ao DAO para inserir NOVO registro no banco
     *atualizarUsuario: Prepara dados e envia ao DAO para atualizar registro EXISTENTE no banco
     *desativarUsuario: Prepara os dados e envia ao DAO para desativar um Usuario*/
     /* ('id', 'cpf' , 'nome' ,'sobrenome' ,'email' , 'logradouro' ,
                    'bairro' , 'numero' , 'complemento' ,'cep' );"*/
    public boolean inserirPessoa(Pessoa pessoa){
        ContentValues cv = new ContentValues ();
       // cv.put("id", pessoa.getId ());
        cv.put("cpf", pessoa.getCpf ());
        cv.put("nome", pessoa.getNome ());
        cv.put("sobrenome", pessoa.getSobrenome ());
        cv.put("email",pessoa.getEmail ());
        cv.put("logradouro",pessoa.getLogradouro ());
        cv.put("bairro",pessoa.getBairro ());
        cv.put("numero",pessoa.getNumero ());
        cv.put("complemento",pessoa.getComplemento ());
        cv.put("cep",pessoa.getCep ());
        cv.put("caminhoFotoPessoa",pessoa.getCaminhoFotoPessoa ());

        return  banco.inserir ( tabela,cv );
    }
   public boolean atualizarPessoa(Pessoa pessoa){
       String where = " cpf = "+ pessoa.getCpf ()+" ";
        ContentValues cv = getCv ( pessoa );
        return banco.atualiza ( tabela,cv,where );
    }

    public boolean deletarPessoa(Pessoa pessoa){
        String where = "id = "+ pessoa.getId ()+" ";
            return banco.deletar ( tabela, where );
    }

    private ContentValues getCv(Pessoa pessoa){
        ContentValues cv = new ContentValues (  );
        try {
            cv.put ( "id", pessoa.getId () );
            cv.put ( "cpf", pessoa.getCpf () );
            cv.put ( "nome", pessoa.getNome () );
            cv.put ( "sobrenome", pessoa.getSobrenome () );
            cv.put ( "email", pessoa.getEmail () );
            cv.put ( "logradouro", pessoa.getLogradouro () );
            cv.put ( "bairro", pessoa.getBairro () );
            cv.put ( "numero", pessoa.getNumero () );
            cv.put ( "complemento", pessoa.getComplemento () );
            cv.put ( "cep", pessoa.getCep () );
            cv.put ("caminhoFotoPessoa",pessoa.getCaminhoFotoPessoa ());
        }catch (Exception e){
            return cv;
        }
        return cv;
    }



}
