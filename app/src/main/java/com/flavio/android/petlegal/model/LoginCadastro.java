package com.flavio.android.petlegal.model;

public class LoginCadastro extends Login {

    private String confirmaSenha;

    public LoginCadastro(String cpf, String senha, String confirmaSenha) {
        setCpf(cpf);
        setSenha(senha);
        setConfirmaSenha(confirmaSenha);
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }
}
