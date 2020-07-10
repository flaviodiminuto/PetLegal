package com.flavio.android.petlegal.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.flavio.android.petlegal.R;
import com.flavio.android.petlegal.controll.ControllerLogin;
import com.flavio.android.petlegal.model.Login;
import com.flavio.android.petlegal.util.SendMessage;

public class Cadastro extends AppCompatActivity {

    ControllerLogin controller = new ControllerLogin();
    EditText campo_cpf;
    EditText campo_senha;
    EditText campo_confirma_senha;
    Button botao_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        iniciarCampos();
        configurarBotaoRegistrar();
    }

    private void configurarBotaoRegistrar() {
        botao_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cadastrar()) {
                    onBackPressed();
                }
            }
        });
    }

    private boolean cadastrar() {
        if(!validaCPF() || !validaSenha()) return false;

        Login login = new Login(
                campo_cpf.getText().toString(),
                campo_senha.getText().toString()
        );
        return controller.salvarLogin(login);
    }


    private boolean validaSenha() {
        String senha = campo_senha.getText().toString();
        String confirmacao = campo_confirma_senha.getText().toString();
        if(senha.isEmpty()){
            SendMessage.toastLong(this, "Preencha o campo senha");
            return false;
        }
        if (confirmacao.isEmpty()){
            SendMessage.toastLong(this, "Confirme a senha");
            return false;
        }
        if(!campo_senha.getText().toString().equals(campo_confirma_senha.getText().toString())){
            SendMessage.toastLong(this,"Confirmação está incorreta!");
            return false;
        }
        return true;
    }

    private boolean validaCPF() {
        try {
            Integer.parseInt(campo_cpf.getText().toString());
            return true;
        }catch (NumberFormatException e){
        }
        SendMessage.toastLong(this,"Adicione um CPF válido!");
        return false;
    }

    private void iniciarCampos(){
        setContentView ( R.layout.activity_cadastro );
        this.campo_cpf = findViewById(R.id.cadastro_cpf);
        this.campo_senha = findViewById(R.id.cadastro_password);
        this.campo_confirma_senha = findViewById(R.id.cadastro_password_confirm);
        this.botao_registrar = findViewById(R.id.cadastro_botao_registrar);
    }

}
