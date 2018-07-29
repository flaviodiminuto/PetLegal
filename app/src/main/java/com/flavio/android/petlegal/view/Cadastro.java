package com.flavio.android.petlegal.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.flavio.android.petlegal.R;
import com.flavio.android.petlegal.controll.ControlaPessoa;
import com.flavio.android.petlegal.controll.ControlaUsuario;
import com.flavio.android.petlegal.model.Pessoa;
import com.flavio.android.petlegal.model.Usuario;

public class Cadastro extends AppCompatActivity {

    EditText txtCpf, txtPassword, txtPasswordConfirm,txtNome,txtSobrenome, txtEmail, txtLogradouro,txtBairro, txtNumero, txtComplemento, txtCep,caminhoFotoPessoa;
    Button btnCadastro;
    private String password, passwordConfirm, nome,sobrenome, email, logradouro, bairro, complemento;
    private int  cpf, cep,numero;
    ControlaUsuario cu;
    ControlaPessoa cp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_cadastro );

        cp = new ControlaPessoa ( getApplicationContext () );
        cu = new ControlaUsuario ( getApplicationContext ()) ;

        /**Estes são os dados para usuario*/
        txtCpf = (EditText)findViewById ( R.id.txtCpf );
        txtPassword = (EditText)findViewById ( R.id.txtPassword );
        txtPasswordConfirm = (EditText)findViewById ( R.id.txtPasswordConfirm );

        /**Estes são os dados pessoais*/
        txtNome = (EditText) findViewById ( R.id.txtNome );
        txtSobrenome = (EditText) findViewById ( R.id.txtSobrenome ) ;
        txtEmail = (EditText) findViewById ( R.id.txtEmail );
        txtLogradouro = (EditText) findViewById ( R.id.txtLogradouro );
        txtBairro = (EditText) findViewById ( R.id.txtBairro );
        txtNumero = (EditText) findViewById ( R.id.txtNumero );
        txtComplemento = (EditText) findViewById ( R.id.txtComplemento );
        txtCep = (EditText) findViewById ( R.id.txtCep );
        //caminhoFotoPessoa = (EditText)findViewById(R.id.txtCaminhoFotoPessoa);

        /**Botão cadastro*/
        btnCadastro = (Button) findViewById ( R.id.btnCadastrar );

        btnCadastro.setOnClickListener (new View.OnClickListener (){
            @Override
            public void onClick(View view) {

               if( cadastrarPessoa ()){
                   sendMessage("Cadastro realizado com sucesso");
                   Intent intent = new Intent ( Cadastro.this, Inicio.class );
                   startActivity ( intent );
               }
            }
        });

    }

    /** EditText txtCpf
     *  txtPassword
     *  txtPasswordConfirm
     *  txtNome
     *  txtEmail
     *  txtLogradouro
     *  txtBairro, txtNumero
     *  txtComplemento
     *  txtCep;
     *Button btnCadastro;
     */
    public boolean cadastrarPessoa(){


        cpf = returnInt ( txtCpf.getText ().toString () );
        nome = txtNome.getText ().toString ();
        sobrenome = txtSobrenome.getText ().toString ();
        email = txtEmail.getText ().toString ();
        logradouro = txtLogradouro.getText ().toString ();
        bairro = txtBairro.getText ().toString ();
        numero = returnInt ( txtNumero.getText ().toString () );
        complemento = txtComplemento.getText ().toString ();
        cep = returnInt ( txtCep.getText ().toString () );

        password = txtPassword.getText ().toString ();
        passwordConfirm = txtPasswordConfirm.getText ().toString ();

        if(password.equals ( passwordConfirm )) {
            Pessoa pess = new Pessoa ( -1, cpf, nome, sobrenome, email, logradouro, bairro, numero, complemento, cep,"" );

            if( pess.getCpf ()!= cp.consultarPessoa ( pess ).getCpf ()){
                boolean criapess, criauser;

                criapess = cp.inserirPessoa ( pess );
                pess = cp.consultarPessoa ( pess );

                Usuario user =new Usuario ( pess.getId (),3,cpf,password,1 );
                criauser = cu.inserirUsuario ( user );
                user = cu.consultarUsuario ( user );

                if( criapess && criauser){
                    Toast.makeText ( this, "Novo usuario Inserido ", Toast.LENGTH_SHORT ).show ();
                    Log.i("Usuario : ", "ID "+ user.getIdPessoa ()+" Inserido");
                    return true;
                }else{
                    Toast.makeText ( this, "Usuario Não foi criado", Toast.LENGTH_SHORT ).show ();
                    return false;
                }
            }else{
                Toast.makeText ( this, "CPF Já cadastrado", Toast.LENGTH_SHORT ).show ();
                return false;
            }
        }else {
            Toast.makeText ( this, "Senha e confirmação não coincidem", Toast.LENGTH_SHORT ).show ();
            return false;
        }
    }


    public void sendMessage(String texto){
        Toast.makeText ( this,texto,Toast.LENGTH_SHORT).show ();
    }

    /**valida valores numericos para variaveis númericas*/
    public int returnInt(String texto){
        int valor;
        if(!texto.isEmpty () && texto.matches ( "[0-9]{" + texto.length () + "}" )) {
            valor = Integer.parseInt ( texto );
        }else{
            valor =0;
        }

        return valor;
    }
}
