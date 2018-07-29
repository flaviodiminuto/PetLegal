package com.flavio.android.petlegal.model;

/**
 * Created by Flávio on 24/05/2018.
 *
 * A classe Pessoa representa a pessoa que acessa o sistema e possui ou não
 * um PET para ser atendido pelo petshop, veterinário ou serviço de banho.
 * Pessoa sem um pet também pode ser usuário pois o serviço de compra não é
 * é restrito para quem te pet.
 * @param id
 * @param cpf
 * @param nome
 * @param sobrenome
 * @param email
 * @param logradouro
 * @param bairro
 * @param numero
 * @param complemento
 * @param cep
 * @param caminhoFoto
 */

public class Pessoa {
    private int id;
    private int cpf;
    private String nome;
    private String sobrenome;
    private String email;
    private String logradouro;
    private String bairro;
    private int numero;
    private String complemento;
    private int cep;
    private String caminhoFotoPessoa;

    public String getCaminhoFotoPessoa() {
        return caminhoFotoPessoa;
    }

    public void setCaminhoFotoPessoa(String caminhoFotoPessoa) {
        this.caminhoFotoPessoa = caminhoFotoPessoa;
    }

    /**Construtor completo
     * @param id
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
     *
     */
    public Pessoa(int id,int cpf, String nome,String sobrenome, String email, String logradouro, String bairro, int numero, String complemento, int cep,String caminhoFotoPessoa) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.caminhoFotoPessoa = caminhoFotoPessoa;

    }

    /**Construtor recebendo somente id (geralmente utilizado na criação de usuario pois um usuario será
     * sempre associado a uma pessoa, assim ao criar um usuário criamos já uma pessoa que terá seus dados
     * preenchidos após a criação do usuário).
     * @param cpf
     */

    public Pessoa (int cpf){
        this.id = -1;
        this.cpf = cpf;
        this.nome = "nome";
        this.sobrenome = "sobrenome";
        this.logradouro = "logradouro";
        this.email = "email@email.com";
        this.bairro ="bairro";
        this.numero = 0;
        this.complemento = "complemento";
        this.cep = 0;
        this.caminhoFotoPessoa = "";
    }

    /**Construtor sem argumentos
     *
     */
    public Pessoa (){
        this.id = -1;
        this.cpf = 0;
        this.nome = "nome";
        this.sobrenome = "sobrenome";
        this.email = "email@email.com";
        this.logradouro = "logradouro";
        this.bairro ="bairro";
        this.numero = 0;
        this.complemento = "complemento";
        this.cep = 0;
        this.caminhoFotoPessoa = "";

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id; }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {this.cpf = cpf; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
}
