package com.flavio.android.petlegal.model;

/**
 * Created by Flávio on 24/05/2018.
 *
 * A classe usuário é o login e senha de acesso que cada pessoa utiliza
 * sendo que uma pessoa pode criar mais de um usuário porém um usuário pertence
 * somente a uma pessoa. Possui os campos, idPessoa que identifica a quem o usuario
 * pertence, login e senha não necessitam ser mais detalhados.
 * @param idPessoa
 * @param tipo
 * @param cpf
 * @param senha
 * @param status
 */


public class Usuario {

    private int idPessoa;
    private int tipo;
    private int cpf;
    private String senha;
    private int status;


    //Construtor completo
    public Usuario(int id,int tipo, int cpf, String senha,int status){
        this.idPessoa = id;
        this.tipo = tipo;
        this.cpf = cpf;
        this.senha = senha;
        this.status =status;
    }
    public Usuario(Usuario user){
        this.idPessoa = user.getIdPessoa ();
        this.tipo = user.getTipo ();
        this.cpf = user.getCpf ();
        this.senha = user.getSenha ();
        this.status = user.getStatus ();
    }
    //Construtor recebendo login e senha
    public Usuario(int cpf, String senha,int tipo){
        this.idPessoa = 0;
        this.tipo = tipo;
        this.cpf = cpf;
        this.senha = senha;
        this.status = 1;
    }
   //Construtor sem argumentos
    public Usuario(){
        this.idPessoa = -1;
        this.tipo = 1;
        this.cpf = -1;
        this.senha = "";
        this.status = 0;
   }


    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    @Override
    public String toString(){
        String txtStatus ;
        if(this.status ==0){
            txtStatus = "(Inativo)";
        }else
            txtStatus = " Ativo";
        String txtTipo;
        switch (this.getTipo ()){
            case 1:
                txtTipo = "Administrador";
                break;
            case 2:
                txtTipo = "Funcionario";
                break;
            default:
                txtTipo = "Usuario";
        }
        return "ID: "+this.getIdPessoa ()+"  -  Usuario: "+this.getCpf ()  +" ("+txtTipo+") "+txtStatus;
    }
}
