package com.flavio.android.petlegal.model;

/**
 * Created by Flávio on 24/05/2018.
 */

public class Pet {

    private int id;
    private int idPessoa;
    private double peso;
    private String cor;
    private String nome;
    private String raca;
    private String porte;
    private String caminhoFotoPet;

    public Pet() {
        this.id = -1;
        this.idPessoa = -1;
        this.peso = 0;
        this.cor = "cor";
        this.nome = "sem nome";
        this.raca = "sem raca";
        this.porte = "sem porte";
        this.caminhoFotoPet = "";
    }

    /**
     * @param id
     * @param idPessoa
     * @param peso
     * @param cor
     * @param nome
     * @param raca
     * @param porte
     * @param caminhoFotoPet
     */
    public Pet(int id, int idPessoa, double peso, String cor, String nome, String raca, String porte,String caminhoFotoPet) {
        this.id = id;
        this.idPessoa = idPessoa;
        this.peso = peso;
        this.cor = cor;
        this.nome = nome;
        this.raca = raca;
        this.porte = porte;
        this.caminhoFotoPet = caminhoFotoPet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getCaminhoFotoPet() {
        return caminhoFotoPet;
    }

    public void setCaminhoFotoPet(String caminhoFotoPet) {
        this.caminhoFotoPet = caminhoFotoPet;
    }


    @Override
    public String toString(){
        return "Nome: "+this.nome+"\t\t\t Raça: "+this.raca+"\nRaça: "+this.raca+"\t\t\tPorte: "+this.porte+"\tPeso: "+this.peso+"kg";
    }

}
