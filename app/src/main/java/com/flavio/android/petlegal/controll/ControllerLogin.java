package com.flavio.android.petlegal.controll;

import com.flavio.android.petlegal.model.LoginCadastro;
import com.flavio.android.petlegal.usecase.persist.LoginCadastroSaveUseCase;

import retrofit2.Call;

public class ControllerLogin {

    private static LoginCadastroSaveUseCase useCase = new LoginCadastroSaveUseCase();

    public Call<Void> cadastrar(LoginCadastro cadastro){
        LoginCadastroSaveUseCase usecase = new LoginCadastroSaveUseCase();
        return usecase.save(cadastro);
    }

}
