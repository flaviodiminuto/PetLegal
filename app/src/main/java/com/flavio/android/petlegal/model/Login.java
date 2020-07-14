package com.flavio.android.petlegal.model;

public class Login {

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
