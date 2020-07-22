package com.flavio.android.petlegal.view;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.flavio.android.petlegal.R;
import com.flavio.android.petlegal.controll.ControlaPessoa;
import com.flavio.android.petlegal.controll.ControlaUsuario;
import com.flavio.android.petlegal.model.Pessoa;

import de.hdodenhof.circleimageview.CircleImageView;

public class User extends AppCompatActivity {
    int id;
    String url;
    TextView nome,visiteSite;
    ImageButton perfil,compras, pets, servicos;
    CircleImageView foto;

    //camera
    private String localArquivoFoto;

    ControlaPessoa cp;
    Pessoa pessoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_user );

        perfil = findViewById ( R.id.btnPerfil );
        compras = findViewById ( R.id.btnCompras );
        pets = findViewById ( R.id.btnPets );
        servicos = findViewById ( R.id.btnServicos );
        nome = findViewById ( R.id.txtNome );
        visiteSite = findViewById ( R.id.txtVisiteSite );
        foto = (CircleImageView)findViewById ( R.id.imgFotoUser);
        cp = new ControlaPessoa ( getApplicationContext () );;
        Bundle bundle = getIntent ().getExtras ();
        id = bundle.getInt ( "id" );
        url = "https://st3.depositphotos.com/4211323/17901/v/1600/depositphotos_179016608-stock-illustration-cat-strong-cool-serious-pet.jpg";
        pessoa = cp.returnPessoaById ( id);
        nome.setText ( pessoa.getNome () );

        /**
         * NECESSÁRIO COLETAR O VALOR DO IDPESSOA TRANSMITIDO PELO USUARIO DA ACTIVITY INICIO ATRAVES
         * DA INTENT IT PARA UTILIZAR NO CARREGAMENTO DE PESSOA NESTA ACTIVITY
         *
         */

        perfil.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent it = new Intent ( User.this,UserPerfil.class );
                it.putExtra ( "id",id );
                startActivity ( it );

            }
        } );
        pets.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent ( User.this, PetPerfil.class );
                intent.putExtra ( "id",id );
                startActivity ( intent );
            }
        } );
        visiteSite.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData( Uri.parse(url));
                startActivity ( intent );
            }
        } );
        compras.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                sendMessage ( "A sessão de COMPRAS não está disponível no momento" );
            }
        } );
        servicos.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                sendMessage ( "A sessão de SERVIÇOS não está disponível no momento" );
            }
        } );

        localArquivoFoto = pessoa.getCaminhoFotoPessoa ();
        carregaImagem ();

    }
    public void sendMessage(String texto){
        Toast.makeText ( this, texto, Toast.LENGTH_SHORT ).show ();
    }
    public void carregaImagem(){
            if(localArquivoFoto!=null && !localArquivoFoto.equals ( "" )) {
                Bitmap imagemFoto = BitmapFactory.decodeFile ( localArquivoFoto );
                Bitmap imagemFotoReduzida = Bitmap.createScaledBitmap ( imagemFoto, 600, 300, false );
                foto.setImageBitmap ( imagemFotoReduzida );
                foto.setTag ( localArquivoFoto );
                //foto1.setScaleType(ImageView.ScaleType.FIT_XY);
            }
    }
    @Override
    public void onBackPressed() {
        ControlaUsuario cu = new ControlaUsuario ( getApplicationContext () );
        int user = cu.consultraUsuarioById ( id ).getTipo ();
        Intent intent;
        if (user != 1) {
            intent = new Intent ( User.this, Inicio.class );
        } else{
            intent = new Intent ( User.this, AdminTesteMVC.class );
         }
            intent.putExtra ( "cpf",pessoa.getCpf () );
            startActivity ( intent );

    }
  /*  public  Bitmap carrega(String caminhoFoto) {
        ExifInterface exif = new ExifInterface(caminhoFoto);
        String orientacao = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
        int codigoOrientacao = Integer.parseInt(orientacao);

        switch (codigoOrientacao) {
            case ExifInterface.ORIENTATION_NORMAL:
                return abreFotoERotaciona(caminhoFoto, 0);
            case ExifInterface.ORIENTATION_ROTATE_90:
                return abreFotoERotaciona(caminhoFoto, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return abreFotoERotaciona(caminhoFoto, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return abreFotoERotaciona(caminhoFoto, 270);
        }
        return abreFotoERotaciona ( caminhoFoto,codigoOrientacao);
    }

    private Bitmap abreFotoERotaciona(String caminhoFoto, int angulo) {
        // Abre o bitmap a partir do caminho da foto
        Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);

        // Prepara a operação de rotação com o ângulo escolhido
        Matrix matrix = new Matrix ();
        matrix.postRotate(angulo);

        // Cria um novo bitmap a partir do original já com a rotação aplicada
        return Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(),
                matrix, true);
    }*/

}
