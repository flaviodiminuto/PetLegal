package com.flavio.android.petlegal.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.flavio.android.petlegal.R;
import com.flavio.android.petlegal.controll.ControlaPessoa;
import com.flavio.android.petlegal.model.Pessoa;

public class TesteMVCPessoa extends AppCompatActivity {
    Button btnConsultar, btnInserir,btnAtualizar,btnDeletar;
    ControlaPessoa cp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_teste_mvcpessoa );
        cp = new ControlaPessoa ( getApplicationContext () );

        btnConsultar = (Button)findViewById ( R.id.btnConsultarPetMVC ) ;
        btnInserir = (Button) findViewById ( R.id.btnInserirPetMVC );
        btnAtualizar =  (Button)findViewById ( R.id.btnAtualizarPetMVC );
        btnDeletar = (Button)findViewById ( R.id.btnDeletarPetMVC ) ;

        if(cp.criaTabelaPessoa ()){
            Toast.makeText ( this, "Tabela Pessoa Criada", Toast.LENGTH_SHORT ).show ();
        }else
            Toast.makeText ( this, "Tabela Pessoa não pôde ser criada", Toast.LENGTH_SHORT ).show ();
        btnConsultar.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(consultarPessoaMVC ()){
                    Toast.makeText ( TesteMVCPessoa.this, "Pessoa consultada ", Toast.LENGTH_SHORT ).show ();
                }else{
                    Toast.makeText ( TesteMVCPessoa.this, "Pessoa ainda não foi inserida", Toast.LENGTH_SHORT ).show ();
                }
            }
        } );
        btnInserir.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(inserirPessoaMVC ()){
                    Toast.makeText ( TesteMVCPessoa.this, "Pessoa inserida", Toast.LENGTH_SHORT ).show ();
                }else{
                    Toast.makeText ( TesteMVCPessoa.this, "Pessoa não pôde ser inserida", Toast.LENGTH_SHORT ).show ();
                }

            }
        } );
        btnAtualizar.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                if(atualizarPessoaMVC ()){
                    Toast.makeText ( TesteMVCPessoa.this, "Pessoa Atualizada", Toast.LENGTH_SHORT ).show ();
                }else{
                    Toast.makeText ( TesteMVCPessoa.this, "Pessoa não pôde ser atualizada", Toast.LENGTH_SHORT ).show ();
                }
            }
        } );
        btnDeletar.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
            if(deletarPessoaMVC ()){
                Toast.makeText ( TesteMVCPessoa.this, "Pessoa deletada com sucesso", Toast.LENGTH_SHORT ).show ();
            }else
                Toast.makeText ( TesteMVCPessoa.this, "Pessoa não pôde ser deletada", Toast.LENGTH_SHORT ).show ();
            }
        } );
    }
    private boolean consultarPessoaMVC(){
        Pessoa pessoa = new Pessoa ( -1,999,"pessoa","nova","pessoa@email.com","Rua da pessoa","Bairro da pessoa", 1,"Casa 2", 333,"");
        if(pessoa.getCpf () ==cp.consultarPessoa ( pessoa ).getCpf ()){
            return true;
        }else{
            return false;
        }

    }
    private boolean inserirPessoaMVC(){
        Pessoa pessoa = new Pessoa ( -1,999,"pessoa","nova","pessoa@email.com","Rua da pessoa","Bairro da pessoa", 1,"Casa 2", 333,"");
          if(pessoa.getCpf ()!=cp.consultarPessoa ( pessoa ).getCpf ()){
           if (cp.inserirPessoa ( pessoa )) {
               Toast.makeText ( this, "Inserindo "+pessoa.getNome (), Toast.LENGTH_SHORT ).show ();
               return true;
           } else {
               return false;
           }
          }else {
              Toast.makeText ( this, "Pessoa já existe", Toast.LENGTH_SHORT ).show ();
              return false;
          }
    }
    private boolean atualizarPessoaMVC() {
        Pessoa pessoa = new Pessoa ( -1, 999, "atualizada", "nova", "pessoa@email.com", "Rua da pessoa", "Bairro da pessoa", 1, "Casa 2", 333,"" );
        if (pessoa.getCpf () == cp.consultarPessoa ( pessoa ).getCpf ()) {
            Toast.makeText ( this, "Atualizando pessoa", Toast.LENGTH_SHORT ).show ();
            return cp.atualizarPessoa ( pessoa );
        } else {
            Toast.makeText ( this, "Pessoa não existe", Toast.LENGTH_SHORT ).show ();
            return false;
        }
    }
    private boolean deletarPessoaMVC(){
        Pessoa pessoa = new Pessoa ( -1, 999, "atualizada", "nova", "pessoa@email.com", "Rua da pessoa", "Bairro da pessoa", 1, "Casa 2", 333,"" );
        if (cp.consultarPessoa ( pessoa ).getNome ().equals ( pessoa.getNome () )) {
            Toast.makeText ( this, "Excluindo "+pessoa.getNome (), Toast.LENGTH_SHORT ).show ();
            return cp.deletarPessoa ( pessoa );
        }else{
            Toast.makeText ( this, "Atualize pessoa para excluir", Toast.LENGTH_SHORT ).show ();
            return false;
        }

    }
}
