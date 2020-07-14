package com.flavio.android.petlegal.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.flavio.android.petlegal.R;
import com.flavio.android.petlegal.controll.ControllerLogin;
import com.flavio.android.petlegal.model.Login;
import com.flavio.android.petlegal.util.DoneOptionUtil;
import com.flavio.android.petlegal.util.MaskEditUtil;
import com.flavio.android.petlegal.util.SendMessage;

public class Inicio extends AppCompatActivity {

    EditText edit_text_cpf, edit_text_senha; // cpf e senha do View
    Button botao_cadastrar;
    Button botao_logar;
    ControllerLogin controllerLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_inicio );
        botao_cadastrar = findViewById ( R.id.cadastro_botao_registrar);
        botao_logar = findViewById ( R.id.btnLogin );
        edit_text_cpf =  findViewById ( R.id.cadastro_cpf);
        edit_text_senha = findViewById ( R.id.cadastro_password);
        controllerLogin = new ControllerLogin();

        configurarCampoCPF();
        conrigurarBotaoLogin();
        configurarBotaoCadastro();
        DoneOptionUtil.configurarDone(this.edit_text_senha, botao_logar);

    }

    private void configurarBotaoCadastro() {
        botao_cadastrar.setOnClickListener (new View.OnClickListener (){
            @Override
            public void onClick(View view) {
                Intent it = new Intent ( Inicio.this, Cadastro.class );
                startActivity ( it );
            }
        });
    }

    private void conrigurarBotaoLogin() {
        botao_logar.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                autenticar();
            }
        } );
    }

    private void configurarCampoCPF() {
        this.edit_text_cpf.addTextChangedListener(MaskEditUtil.mask(this.edit_text_cpf,MaskEditUtil.FORMAT_CPF));
    }

    public Login getLogin(){
        String cpf = MaskEditUtil.unmask(edit_text_cpf.getText ().toString());
        String senha = edit_text_senha.getText ().toString ();

        if(cpf.isEmpty () || cpf.matches ( "[0-9]{11}" )) {
            cpf = "";
        }

        return new Login(cpf,senha);
    }

    private void autenticar(){
        Login login = getLogin();
        String token = getToken(login);
        if(controllerLogin.valida(login) && !token.isEmpty()){
            redirecionaParaHome(token);
        }else{
            SendMessage.toastLong(this,"Combinacao Login e senha incorreta!");
        }
    }

    private String getToken(Login login) {
        //todo - - sistema de token
        return "teste";
    }

    private void redirecionaParaHome(String token){
        Intent it = new Intent(this, Home.class);
        it.putExtra("token", token);
        startActivity(it);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }
}
