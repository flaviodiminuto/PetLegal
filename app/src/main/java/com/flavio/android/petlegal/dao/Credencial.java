package com.flavio.android.petlegal.dao;

import java.time.LocalDateTime;

public class Credencial {
    private Long id;
    private String passe;
    private LocalDateTime data_emissao;
    private LocalDateTime data_expiracao;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPasse() {
        return passe;
    }

    public void setPasse(String passe) {
        this.passe = passe;
    }

    public LocalDateTime getData_emissao() {
        return data_emissao;
    }

    public void setData_emissao(LocalDateTime data_emissao) {
        this.data_emissao = data_emissao;
    }

    public LocalDateTime getData_expiracao() {
        return data_expiracao;
    }

    public void setData_expiracao(LocalDateTime data_expiracao) {
        this.data_expiracao = data_expiracao;
    }

    @Override
    public String toString() {
        return "Credencial{" +
                ", passe='" + passe + '\'' +
                ", data_emissao=" + data_emissao +
                ", data_expiracao=" + data_expiracao +
                '}';
    }
}
