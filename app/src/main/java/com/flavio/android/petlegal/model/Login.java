package com.flavio.android.petlegal.model;

public class Login {

    Long id;
    String cpf;
    String senha;

    public Login() {
        cpf = "";
        senha = "";
    }

    public Login(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Login{" +
                "cpf='" + cpf + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
