package com.flavio.android.petlegal.controll;

import com.flavio.android.petlegal.interfaces.UseCase;
import com.flavio.android.petlegal.model.Login;
import com.flavio.android.petlegal.model.LoginCadastro;
import com.flavio.android.petlegal.usecase.CadastrarLogin;

public class ControllerLogin {

    public boolean valida(Login login){
        Login aux = findLogin(login);
        return (aux != null && aux.getCpf().equals(login.getCpf()));
    }

    private Login findLogin(Login login){
        //todo - fazer a requisição para um servico que retorne o usuário
        return new Login();
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
