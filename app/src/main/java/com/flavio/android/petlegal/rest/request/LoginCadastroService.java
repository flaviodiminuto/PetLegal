package com.flavio.android.petlegal.rest.request;

import com.flavio.android.petlegal.model.LoginCadastro;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginCadastroService {
    @Headers({"Content-type: application/json"})
    @POST("logins")
    Call<Void> postAsync(@Body LoginCadastro cadastro);

}
