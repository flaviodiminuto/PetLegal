package com.flavio.android.petlegal.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.flavio.android.petlegal.R;
import com.flavio.android.petlegal.controll.ControllerLogin;
import com.flavio.android.petlegal.model.LoginCadastro;
import com.flavio.android.petlegal.util.DoneOptionUtil;
import com.flavio.android.petlegal.util.MaskEditUtil;
import com.flavio.android.petlegal.util.SendMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cadastro extends AppCompatActivity{

    private ControllerLogin controller;
    private EditText campo_cpf;
    private EditText campo_senha;
    private EditText campo_confirma_senha;
    private Button botao_registrar;
    private Button botao_cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        iniciarCampos();
        this.controller = new ControllerLogin();

        configurarCampoCPF();
        configurarBotaoRegistrar();
        configurarBotaoCancelar();
        DoneOptionUtil.configurarDone(this.campo_confirma_senha,botao_registrar);

    }

    private void configurarBotaoCancelar() {
        this.botao_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
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
        this.botao_cancelar = findViewById(R.id.cadastro_botao_cancelar);
    }


    private void configurarBotaoRegistrar() {
        botao_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validaCampos()) {
                    sendMessage("Realizando o cadastro");
                    Call<Void> call = submeterCadastro();
                    avaliarRequisicao(call);
                }
            }
        });
    }

    private void avaliarRequisicao(Call<Void> call) {
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int code = response.code();
                Log.e("Response", "Sttus = "+ code);
                switch (code){
                    case 201:
                        sendMessage("Cadastro realizado com sucesso");
                        redirecionaParaHome();
                        break;
                    case 406:
                        sendMessage("CPF já cadastrado");
                        break;
                    case 500 :
                        sendMessage("Serviço indisponível, tente mais tarde");
                        break;
                    default:
                        sendMessage("Queremos muito te cadastrar mas não estamos conseguindo");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Response", "Falha durante o cadastro");
            }
        });
    }

    private void redirecionaParaHome() {
        onBackPressed();
    }


    private Call<Void> submeterCadastro(){
        LoginCadastro cadastro= new LoginCadastro(
                this.campo_cpf.getText().toString(),
                this.campo_senha.getText().toString(),
                this.campo_confirma_senha.getText().toString());
        return controller.cadastrar(cadastro );
    }

    private void sendMessage(String message){
        SendMessage.toastLong(this,message);
    }
}
