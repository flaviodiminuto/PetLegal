package com.flavio.android.petlegal.controll;

import com.flavio.android.petlegal.model.Login;
import com.flavio.android.petlegal.model.Usuario;

public class ControllerLogin {

    public Usuario autentica(Login login){
        Usuario usuario = findLogin(login);
        return usuario;
    }

    private Usuario findLogin(Login login){
        //todo - fazer a requisição para um servico que retorne o usuário
        return new Usuario();
    }

    public boolean salvarLogin(Login usuario){
        //salvar usuario

        return true;
    }


}