package com.flavio.android.petlegal.model;

/**
 * Created by Flávio on 24/05/2018.
 *
 * A classe Telefone se faz necessária pois um usuário pode cadastrar
 *uma quantidade indefinida de números de telefones para serem contactados
 *quando o Pet tiver seu atendimento finalizado e precisar ser entregue.
 *Cada número terá um tipo que define se é celular ou fixo , um idPessoa
 *que associará o número a uma pessoa e o número de telefone .
 *@param idTel Identificação única do número registrado
 *@param idPessoa Identificação de quem o número pertence
 *@param tipoTel identifica o tipo de telefone (celular, telefone fixo ou outro)
 *@param numeroTel Número do telefone propriamente
*/

public class Telefone {
    private int idTel;
    private int idPessoa;
    private int tipoTel;
    private int numeroTel;

    public Telefone() {
        this.idTel = -1;
        this.idPessoa = -1;
        this.tipoTel = 1;
        this.numeroTel =0000000000;
    }
    public Telefone(int numeroTel) {
        this.idTel = -1;
        this.idPessoa = -1;
        this.tipoTel = 1;
        this.numeroTel = numeroTel;
    }

    public Telefone(int idTel, int idPessoa, int tipoTel, int numeroTel) {
        this.idTel = idTel;
        this.idPessoa = idPessoa;
        this.tipoTel = tipoTel;
        this.numeroTel = numeroTel;
    }

    public int getIdTel() {
        return idTel;
    }

    public void setIdTel(int idTel) {
        this.idTel = idTel;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public int getTipoTel() {
        return tipoTel;
    }

    public void setTipoTel(int tipoTel) {
        this.tipoTel = tipoTel;
    }

    public int getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(int numeroTel) {
        this.numeroTel = numeroTel;
    }
    @Override
    public String toString(){
        String txtTipo;
        if(this.getTipoTel ()==1)
            txtTipo = "Telefone Fixo";
        else
            txtTipo = "Telefone Celular";

        return txtTipo +": "+ this.getNumeroTel ();
    }
}
