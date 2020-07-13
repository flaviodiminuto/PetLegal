package com.flavio.android.petlegal.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.flavio.android.petlegal.R;
import com.flavio.android.petlegal.controll.ControllerLogin;
import com.flavio.android.petlegal.interfaces.UseCase;
import com.flavio.android.petlegal.model.Login;
import com.flavio.android.petlegal.model.LoginCadastro;
import com.flavio.android.petlegal.usecase.CadastrarLogin;
import com.flavio.android.petlegal.util.DoneOptionUtil;
import com.flavio.android.petlegal.util.MaskEditUtil;
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

        configurarCampoCPF();
        configurarBotaoRegistrar();
        DoneOptionUtil.configurarDone(this.campo_confirma_senha,botao_registrar);

    }

    private void configurarCampoCPF() {
        this.campo_cpf.addTextChangedListener(MaskEditUtil.mask(this.campo_cpf,MaskEditUtil.FORMAT_CPF));
    }


    private boolean validaCampos() {
        String senha = campo_senha.getText().toString();
        String confirmacao = campo_confirma_senha.getText().toString();
        String cpf = MaskEditUtil.unmask(campo_cpf.getText().toString());
        StringBuilder message = new StringBuilder();

        if (cpf.isEmpty()){
            message.append("Preencha o campo CPF\n");
        }
        if(cpf.length() != 11) {
            message.append("Adicione um CPF válido!\n");
        }
        if(senha.isEmpty()){
            message.append("Preencha o campo senha\n");
        }
        if (confirmacao.isEmpty()){
            message.append("Confirme a senha\n");
        }
        if(!campo_senha.getText().toString().equals(campo_confirma_senha.getText().toString())){
            message.append("Confirmação está incorreta!");
        }

        if(message.toString().isEmpty()) return true;

        SendMessage.toastLong(this,message.toString());
        return false;
    }

    private void iniciarCampos(){
        setContentView ( R.layout.activity_cadastro );
        this.campo_cpf = findViewById(R.id.cadastro_cpf);
        this.campo_senha = findViewById(R.id.cadastro_password);
        this.botao_registrar = findViewById(R.id.cadastro_botao_registrar);
        this.campo_confirma_senha = findViewById(R.id.cadastro_password_confirm);
    }


    private void configurarBotaoRegistrar() {
        botao_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validaCampos())
                    submeterCadastro();
            }
        });
    }

    private void submeterCadastro(){
        LoginCadastro cadastro= new LoginCadastro(
                this.campo_cpf.getText().toString(),
                this.campo_senha.getText().toString(),
                this.campo_confirma_senha.getText().toString());
        controller.cadastrar(cadastro );
    }
}
