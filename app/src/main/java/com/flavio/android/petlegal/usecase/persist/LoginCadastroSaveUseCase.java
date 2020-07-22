package com.flavio.android.petlegal.usecase.persist;

import com.flavio.android.petlegal.model.LoginCadastro;
import com.flavio.android.petlegal.rest.client.RetrofitClient;
import com.flavio.android.petlegal.rest.request.LoginCadastroService;

import retrofit2.Call;
import retrofit2.Retrofit;

public class LoginCadastroSaveUseCase {

    public Call<Void> save(LoginCadastro cadastro) {
        Retrofit retrofit = RetrofitClient.getRetrofit();
        LoginCadastroService service = retrofit.create(LoginCadastroService.class);
        return  service.postAsync(cadastro);
    }
}
