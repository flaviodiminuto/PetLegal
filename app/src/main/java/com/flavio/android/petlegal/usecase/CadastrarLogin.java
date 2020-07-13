package com.flavio.android.petlegal.usecase;

import com.flavio.android.petlegal.interfaces.UseCase;
import com.flavio.android.petlegal.model.LoginCadastro;

public class CadastrarLogin implements UseCase {

    private LoginCadastro cadastro;

    public CadastrarLogin(LoginCadastro loginCadastro) {
        this.cadastro = loginCadastro;
    }

    @Override
    public boolean process(){


        return false;
    }
}
