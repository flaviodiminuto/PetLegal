package com.flavio.android.petlegal.rest.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient{
    public static Retrofit getRetrofit() {
            return new  Retrofit.Builder()
                    .baseUrl("https://petlegal-api.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }
}
