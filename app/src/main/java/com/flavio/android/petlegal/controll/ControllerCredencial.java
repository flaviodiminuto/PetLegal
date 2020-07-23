package com.flavio.android.petlegal.controll;

import android.content.Context;

import com.flavio.android.petlegal.dao.Credencial;
import com.flavio.android.petlegal.dao.TokenCrud;

public class ControllerCredencial {
    private TokenCrud crud;
    private Context context;

    public ControllerCredencial(Context context) {
        this.context = context;
        crud = new TokenCrud(context);
    }

    public boolean criarTabelaCredencial(){
        return crud.criarTabela();
    }

    public boolean persistirCredencial(Credencial credencial){
        return crud.persistToken(credencial);
    }

    public Credencial getCredencial(){
        return crud.getCredencial();
    }

}
