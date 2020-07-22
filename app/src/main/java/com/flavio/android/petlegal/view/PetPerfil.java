package com.flavio.android.petlegal.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.flavio.android.petlegal.R;
import com.flavio.android.petlegal.controll.ControlaPet;
import com.flavio.android.petlegal.model.Pet;

import java.util.ArrayList;
import java.util.List;

public class PetPerfil extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int id;
    private String corSelecionada;
    private EditText nome, raca, porte, peso;
    private TextView lblSalvaNovo;
    private Spinner spinnerCor;
    private Button btnAdicionaPet;
    private ListView listaPets;
    private ControlaPet cpet;
    private Pet pet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_pet_perfil );
        cpet = new ControlaPet ( getApplicationContext () );
        pet = new Pet();
        Bundle bundle = getIntent ().getExtras ();
        id = bundle.getInt ( "id" );

        //Apontando as TextBox que temos na View
        lblSalvaNovo = (TextView)findViewById ( R.id.lblNovoAtualizaPet ) ;
        nome = (EditText)findViewById ( R.id.txtPetPerfilNome ) ;
        raca = (EditText)findViewById ( R.id.txtPetPerfilRaca);
        porte = (EditText)findViewById ( R.id.txtPetPerfilPorte);
        peso = (EditText)findViewById ( R.id.txtPetPerfilPeso);
        //Prechendo campos do spinner (Cores)
        spinnerCor = (Spinner)findViewById ( R.id.spinnerPetPerfilCor);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( this,R.array.cores,android.R.layout.simple_spinner_item );
        adapter.setDropDownViewResource ( android.R.layout.simple_list_item_1 );
        spinnerCor.setAdapter ( adapter );
        spinnerCor.setOnItemSelectedListener ( this );
        btnAdicionaPet = (Button)findViewById ( R.id.btnPetPerfilCadastrarPet );
        listaPets = (ListView)findViewById ( R.id.listViewPetPerfilLista );
        listarPets ();

        btnAdicionaPet.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(salvarPet ()){

                    pet = new Pet (  );
                    nome.setText ( "" );
                    raca.setText ( "" );
                    porte.setText ( "" );
                    peso.setText ( "" );
                    listarPets ();
                }else
                {
                    pet = new Pet();
                    nome.setText ( "" );
                    raca.setText ( "" );
                    porte.setText ( "" );
                    peso.setText ( "" );
                    listarPets ();
                }
            }
        } );

        registerForContextMenu(listaPets);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void sendMessage(String texto){
        Toast.makeText ( this, texto, Toast.LENGTH_SHORT ).show ();
    }
    @Override
    protected void onResume() {
        super.onResume();
        listarPets ();
    }
    public boolean preencherPet(){
        boolean completo = true;
        pet.setIdPessoa ( id );
        if(nome.getText ().toString ().isEmpty ())completo = false; else
            pet.setNome ( nome.getText ().toString () );
        if(raca.getText ().toString ().isEmpty ())completo =false; else
            pet.setRaca ( raca.getText ().toString () );
        if(porte.getText ().toString ().isEmpty ())completo = false; else
            pet.setPorte ( porte.getText ().toString () );
        if(peso.getText ().toString ().isEmpty ())completo = false; else
            pet.setPeso ( Double.valueOf ( peso.getText ().toString () ) );
        if(spinnerCor.getSelectedItem ().toString ().isEmpty ())completo = false;
        pet.setCor ( spinnerCor.getSelectedItem ().toString () );

        return completo;
    }
    public boolean salvarPet(){
        boolean completo = preencherPet ();
        if(completo){
            if(cpet.returnPetById ( pet.getId () ).getId ()==pet.getId ()&&pet.getId ()!=-1){
                if (cpet.atualizarPet ( pet )) {
                    sendMessage ( "PET Atualizado com sucesso" );
                    return true;
                }else
                    sendMessage ( "Falha ao atualizar PET" );
                return false;
            }else{
                if (cpet.inserirPet ( pet )) {
                    sendMessage ( "Novo PET Cadastrado!" );
                    return true;
                } else {
                    sendMessage ( "Falha ao cadastrar Novo PET" );
                    return false;
                }
            }
        }else
        {
            sendMessage ( "Preencha todos os campos" );
            return false;
        }
    }


    public void listarPets(){
        List<Pet>listaDePets = new ArrayList<> (  );
        listaDePets = cpet.listaPetsById ( id );
        ArrayAdapter<Pet> adaptador = new ArrayAdapter<Pet> (this,android.R.layout.simple_list_item_1,listaDePets  );
        listaPets.setAdapter ( adaptador );
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, view, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        pet = (Pet) listaPets.getAdapter().getItem(info.position);



        //Editar dados do PET
            MenuItem sms = menu.add ( "Editar" );
            sms.setOnMenuItemClickListener ( new MenuItem.OnMenuItemClickListener () {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    preencherCampos();
                    return false;
                }
            } );


        //Deletar registro do PET
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            //Metodo para deletar
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                cpet.deletar ( pet );
               listarPets ();
                return false;
            }
        });

    }
    public void preencherCampos(){
        nome.setText ( pet.getNome () );
        raca.setText ( pet.getRaca () );
        porte.setText ( pet.getPorte () );
        peso.setText ( String.valueOf ( pet.getPeso () ) );
    }
    public void onBackPressed()
    {
        Intent intent = new Intent ( this, User.class );
        intent.putExtra ( "id",id );
        startActivity ( intent );
    }

}
