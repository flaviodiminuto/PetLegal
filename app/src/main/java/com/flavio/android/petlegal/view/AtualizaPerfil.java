package com.flavio.android.petlegal.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.flavio.android.petlegal.R;
import com.flavio.android.petlegal.controll.ControlaPessoa;
import com.flavio.android.petlegal.controll.ControlaTelefone;
import com.flavio.android.petlegal.controll.ControlaUsuario;
import com.flavio.android.petlegal.model.Pessoa;
import com.flavio.android.petlegal.model.Telefone;
import com.flavio.android.petlegal.model.Usuario;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;


public class AtualizaPerfil extends AppCompatActivity {
    private int id,tipo;
    private EditText email,logradoro,bairro, numeroAtualiza,complemento,cep,novoTelefone,senha, senhaNova,confirmaSenha;
    private TextView nomeCompleto,atualizaFoto;
    private ImageButton btnAdicionaTelefone,btnAplicaAtualizacao,btnAtualizaSenha,btnDesativaUsuario;
    private RadioGroup radioGrupoTelefone;
    private RadioButton rdbTelMovel,rdbTelFixo;
    private de.hdodenhof.circleimageview.CircleImageView foto1;
    //private ImageView foto;


    //camera
    private String localArquivoFoto;
    private static final int TIRA_FOTO = 123;

    ControlaUsuario cu;
    ControlaPessoa cp;
    ControlaTelefone ct;
    Pessoa logado;
    Usuario userLogado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_atualiza_perfil );
        Bundle bundle = getIntent ().getExtras ();
        tipo = 0;
        id = bundle.getInt ( "id" );
        cu = new ControlaUsuario ( getApplicationContext () );
        cp = new ControlaPessoa ( getApplicationContext () );
        ct = new ControlaTelefone ( getApplicationContext () );
        logado = cp.returnPessoaById ( id );
        userLogado = cu.consultraUsuarioById ( id );



        /*Adicionando a programação os itens da View*/
        atualizaFoto = findViewById ( R.id.lblAtualizarFotoAtualizaPerfil );
        nomeCompleto = findViewById ( R.id.lblAtualizaPerfilNomeCompleto );
        email = findViewById ( R.id.txtAtualizaPerfilEmail );
        logradoro = findViewById ( R.id.txtAtualizaPerfilLogradouro );
        bairro = findViewById ( R.id.txtAtualizaPerfilBairro );
        numeroAtualiza = findViewById ( R.id.txtAtualizaPerfilNumeroCasa );
        complemento = findViewById ( R.id.txtAtualizaPerfilComplemento);
        cep =findViewById ( R.id.txtAtualizaPerfilCep);
        novoTelefone = findViewById ( R.id.txtAtualizaPerfilAdicionaTelefon );
        btnAdicionaTelefone = findViewById ( R.id.btnAtualizaPerfilAdicionarNovoNumero );
        btnAplicaAtualizacao = findViewById ( R.id.btnAtualizaPerfilAplicaAtualizacao );
        btnDesativaUsuario = findViewById ( R.id.btnDeletaAtualizaPerfil );
        senha = findViewById ( R.id.txtAtualizaPerfilSenhaAtual );
        senhaNova = findViewById ( R.id.txtAtualizaPerfilNovaSenha );
        confirmaSenha = findViewById ( R.id.txtAtualizaPerfilConfirmaNovaSenha );
        btnAtualizaSenha = findViewById ( R.id.btnAtualizaPerfilTrocarSenha );
        radioGrupoTelefone = (RadioGroup) findViewById ( R.id.RadioGrupo );
        rdbTelMovel = (RadioButton)findViewById ( R.id.radioButtonTelMovel );
        rdbTelMovel.setChecked ( true );
        rdbTelFixo = (RadioButton)findViewById ( R.id.radioButtonTelFixo );

        //foto = findViewById ( R.id.imageViewFoto );
        foto1 =(CircleImageView) findViewById ( R.id.imgFotoAtualizaPerfil );


        /*Adicionao os valores do banco aos campos editáveis*/
        nomeCompleto.setText ( logado.getNome ()+" "+logado.getSobrenome () );
        email.setText ( logado.getEmail () );
        logradoro.setText ( logado.getLogradouro () );
        bairro.setText ( logado.getBairro () );
        numeroAtualiza.setText ( String.valueOf ( logado.getNumero ()  ));
        complemento.setText ( logado.getComplemento () );
        cep.setText (String.valueOf (  logado.getCep () ) );


        radioGrupoTelefone.setOnCheckedChangeListener ( new RadioGroup.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
               if(rdbTelMovel.isChecked ())
                   tipo =0;
               if(rdbTelFixo.isChecked ())
                   tipo=1;
            }
        } );

       btnAplicaAtualizacao.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(atualizaPessoa()) {
                    sendMessage ( "Dados atualizados" );
                    Intent it = new Intent ( AtualizaPerfil.this, UserPerfil.class );
                    it.putExtra ( "id",id );
                    startActivity ( it );
                }
                else
                    sendMessage ( "Falha ao tentar atualizar dados" );
            }
        } );
       btnDesativaUsuario.setOnClickListener ( new View.OnClickListener () {
           @Override
           public void onClick(View view) {
               if(desativar ()){
                   sendMessage ( "Usuario Deletado" );
                   Intent intent = new Intent ( AtualizaPerfil.this, Inicio.class );
                   startActivity ( intent );
               }else{
                   sendMessage ( "Não foi possível deletar usuário" );
               }
           }
       } );
        btnAdicionaTelefone.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(novoTelefone.length ()==8 || novoTelefone.length ()==9) {
                    if (adicionaNovoTelefone ()) {
                        sendMessage ( "Telefone adicionado" );
                        novoTelefone.setText ( "" );
                    } else
                        sendMessage ( "Falha ao adicionar novo Número " );
                }else
                    sendMessage ( "Numero de tenefone inválido\n O número deve conter OITO ou NOVE dígitos" );
            }
        } );

         btnAtualizaSenha.setOnClickListener ( new View.OnClickListener () {
             @Override
             public void onClick(View view) {
                 if(senha.getText ().toString ().equals ( userLogado.getSenha ())){
                     if(senhaNova.getText ().toString ().equals ( confirmaSenha.getText ().toString () )){
                         if(atualizaSenha()){
                             sendMessage (" Senha atualizada" );
                         }else
                             sendMessage ( "Falha na atualização de senha" );
                     }else
                         sendMessage ( "Nova senha e confirmação são diferentes" );
                 }else
                     sendMessage ( "Senha atual incorreta" );
             }
         } );

        atualizaFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             atualizaFoto ();
            }
        });
        foto1.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                atualizaFoto ();
            }
        } );

    localArquivoFoto = logado.getCaminhoFotoPessoa ();
    carregaImagem ();
    }
    public void atualizaFoto(){
        localArquivoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis()+".jpg";
        Toast.makeText ( AtualizaPerfil.this, "Registre sua foto com o aparelho na HORIZONTAL", Toast.LENGTH_LONG).show ();
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri localFoto = Uri.fromFile(new File(localArquivoFoto));
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, localFoto);
        startActivityForResult(takePictureIntent, TIRA_FOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult ( requestCode, resultCode, data );
            if (resultCode == Activity.RESULT_OK)
            {
                carregaImagem();
                logado.setCaminhoFotoPessoa ((String) foto1.getTag());
                cp.atualizarPessoa ( logado );
            }
            else
            {
                this.localArquivoFoto = null;
            }

    }
    public void carregaImagem(){
        if(localArquivoFoto!=null && !localArquivoFoto.equals ( "" )) {
            Bitmap imagemFoto = BitmapFactory.decodeFile ( localArquivoFoto );
            Bitmap imagemFotoReduzida = Bitmap.createScaledBitmap ( imagemFoto, 600, 300, false );
            foto1.setImageBitmap ( imagemFotoReduzida );
            foto1.setTag ( localArquivoFoto );
            //foto1.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }
    public boolean adicionaNovoTelefone(){
        int num = returnInt ( novoTelefone.getText ().toString () );
        try {
            Telefone telefone = new Telefone ( -1, this.id, this.tipo, num );
            if (ct.inserirTelefone ( telefone ))
                return true;
            else
                return false;
        }catch (Exception e){
            return false;
        }
    }
    public void sendMessage(String texto){
        Toast.makeText ( this, texto, Toast.LENGTH_SHORT ).show ();
    }
    public int returnInt(String texto){
        int valor;
        if(!texto.isEmpty () && texto.matches ( "[0-9]{" + texto.length () + "}" )) {
            valor = Integer.parseInt ( texto );
        }else{
            valor =1;
        }

        return valor;
    }
   public boolean desativar(){
        return cu.desativarUsuario ( userLogado );
   }

    public boolean atualizaPessoa(){
        Pessoa atualizaPessoa = logado;
        atualizaPessoa.setEmail ( email.getText().toString () );
        atualizaPessoa.setLogradouro ( logradoro.getText ().toString () );
        atualizaPessoa.setBairro ( bairro.getText ().toString () );
        atualizaPessoa.setNumero ( Integer.valueOf (numeroAtualiza.getText ().toString () ));
        atualizaPessoa.setComplemento ( complemento.getText ().toString () );
        atualizaPessoa.setCep ( Integer.valueOf ( cep.getText ().toString () ) );

        return cp.atualizarPessoa ( atualizaPessoa );

    }
    public boolean atualizaSenha(){
       Usuario usuAtualizado  = userLogado;
       usuAtualizado.setSenha ( senhaNova.getText ().toString () );
        return atualizaUsuario ( usuAtualizado );
    }
    public boolean atualizaUsuario(Usuario atualizado){
        return cu.atualizarUsuario ( atualizado );
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent ( this,UserPerfil.class );
        intent.putExtra ( "id",id );
        startActivity ( intent );
    }
}
