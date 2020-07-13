package com.flavio.android.petlegal.controll;

import com.flavio.android.petlegal.interfaces.UseCase;
import com.flavio.android.petlegal.model.Login;
import com.flavio.android.petlegal.model.LoginCadastro;
import com.flavio.android.petlegal.model.Usuario;
import com.flavio.android.petlegal.usecase.CadastrarLogin;

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

    public boolean cadastrar(LoginCadastro cadastro){
        UseCase useCase = new CadastrarLogin(cadastro);

        return true;
        //todo - completar
    }


}
