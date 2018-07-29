package com.flavio.android.petlegal.view;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.flavio.android.petlegal.R;
import com.flavio.android.petlegal.controll.ControlaPessoa;
import com.flavio.android.petlegal.controll.ControlaTelefone;
import com.flavio.android.petlegal.controll.ControlaUsuario;
import com.flavio.android.petlegal.model.Pessoa;
import com.flavio.android.petlegal.model.Telefone;
import com.flavio.android.petlegal.model.Usuario;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class UserPerfil extends AppCompatActivity {
    private static final int TELEFONE_CODE_REQUEST = 10;
    private CircleImageView foto;
    private ImageButton btnAtualiza;
    private TextView verNoMaps,nomeUsuario,nomeCompleto,email,endereco,bairro,cep,complemento;
    private ListView listaTelefones;

    private int id;
    ControlaPessoa cp;
    ControlaUsuario cu;
    ControlaTelefone ct;
    Pessoa pessoa;
    Usuario user;
    Telefone telefoneSelecionado;

    private String localArquivoFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate ( savedInstanceState );
       setContentView ( R.layout.activity_user_perfil );
        foto = (CircleImageView)findViewById (R.id.imgFotoUserPerfil );
       btnAtualiza = findViewById ( R.id.btnAtualizaUsuarioPerfil );
       nomeUsuario = findViewById ( R.id.txtPerfilUserNome );
        nomeCompleto = findViewById ( R.id.txtUserPerfilNomeCompleto );
        email = findViewById ( R.id.txtUserPerfilEmail );
        endereco = findViewById ( R.id.txtUserPerfilEndereco );
        bairro = findViewById ( R.id.txtUserPerfilBairro );
        complemento = findViewById ( R.id.txtUserPerfilComplemento );
        cep = findViewById ( R.id.txtUserPerfilCep) ;
        listaTelefones = (ListView)findViewById ( R.id.listaUserPerfilTelefones );
        verNoMaps = (TextView) findViewById ( R.id.txtVerMaps );



        cp = new ControlaPessoa ( getApplicationContext () );
        cu = new ControlaUsuario ( getApplicationContext () );
        ct = new ControlaTelefone ( getApplicationContext () );
        Bundle bundle = getIntent ().getExtras ();
        id = bundle.getInt ( "id" );

        listaTelefones();
        /**Carrega pessoa e usuario*/
        this.pessoa = cp.returnPessoaById ( id );
        this.user = cu.consultraUsuarioById ( id );

        localArquivoFoto = pessoa.getCaminhoFotoPessoa ();
        /**Carrega as textViews*/
        nomeUsuario.setText ( pessoa.getNome () );
        nomeCompleto.setText ( pessoa.getNome ()+" "+pessoa.getSobrenome ());
        email.setText ( "Email: "+pessoa.getEmail () );
        endereco.setText ("Endereço: "+ pessoa.getLogradouro ()+"\t Número: "+String.valueOf ( pessoa.getNumero () ) );
        bairro.setText ("Bairro: "+ pessoa.getBairro () );
        complemento.setText ( "Complemento: "+pessoa.getComplemento () );
        cep.setText ("CEP: "+ String.valueOf ( pessoa.getCep () ) );


        //Latitude -23.6632222
        //Longitude -46.7290609  da Fatec zona sul
        verNoMaps.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                Uri gmmIntentUri = Uri.parse("http://maps.google.com/maps?daddr=-23.663183, -46.729104");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        } );
        btnAtualiza.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(UserPerfil.this, AtualizaPerfil.class);
                it.putExtra ( "id",id);
                startActivity ( it );
            }
        } );

        registerForContextMenu(listaTelefones);
        carregaImagem ();
    }


    public void sendMessage(String texto){
        Toast.makeText ( this, texto, Toast.LENGTH_SHORT ).show ();
    }
    public void listaTelefones(){

        List<Telefone> telefones = new ArrayList<> (  );
        telefones = ct.listaTelefones ( id );
        ArrayAdapter<Telefone> adaptador = new ArrayAdapter<Telefone> (this,android.R.layout.simple_list_item_1,telefones  );
        listaTelefones.setAdapter ( adaptador );



    }
    @Override
    protected void onResume() {
        super.onResume();
        listaTelefones ();
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, view, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        telefoneSelecionado = (Telefone) listaTelefones.getAdapter().getItem(info.position);

        //Ligar
        MenuItem ligar = menu.add("Ligar");

        ligar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

         @Override
         public boolean onMenuItemClick(MenuItem item) {
                 String permissaoLigacao = android.Manifest.permission.CALL_PHONE;
                 if (ActivityCompat.checkSelfPermission(UserPerfil.this, permissaoLigacao) == PackageManager.PERMISSION_GRANTED) {
                     fazerLigacao();
                 } else {
                     ActivityCompat.requestPermissions(UserPerfil.this, new String[]{permissaoLigacao}, TELEFONE_CODE_REQUEST);
                 }
                 return false;
             }
         }
        );

        //Enviar SMS
        if(telefoneSelecionado.getTipoTel ()==0) {
            MenuItem sms = menu.add ( "Enviar SMS" );
            Intent intentSms = new Intent ( Intent.ACTION_VIEW );
            intentSms.setData ( Uri.parse ( "sms:" + telefoneSelecionado.getNumeroTel () ) );
            intentSms.putExtra ( "sms_body", "Mensagem" );
            sms.setIntent ( intentSms );
        }

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            //Metodo para deletar
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                ct.deletaTelefone ( telefoneSelecionado );
                listaTelefones();

                return false;
            }
        });

    }
    private void fazerLigacao() {
        Intent intentLigar = new Intent(Intent.ACTION_CALL);
        intentLigar.setData(Uri.parse("tel:" + telefoneSelecionado.getNumeroTel ())); //CUIDADO O NOME DENTRO DE "" DEVE XTAR EM MINIUSCULA tel
        if (ActivityCompat.checkSelfPermission(UserPerfil.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intentLigar);
    }
    public void carregaImagem() {
        if (localArquivoFoto != null && !localArquivoFoto.equals ( "" )) {
            Bitmap imagemFoto = BitmapFactory.decodeFile ( localArquivoFoto );
            Bitmap imagemFotoReduzida = Bitmap.createScaledBitmap ( imagemFoto, 600, 300, false );
            foto.setImageBitmap ( imagemFotoReduzida );
            foto.setTag ( localArquivoFoto );
            //foto1.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent ( this, User.class );
        intent.putExtra ( "id",id );
        startActivity ( intent );
    }

}
