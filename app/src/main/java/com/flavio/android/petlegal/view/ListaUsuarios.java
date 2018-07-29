package com.flavio.android.petlegal.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.flavio.android.petlegal.R;
import com.flavio.android.petlegal.controll.ControlaPessoa;
import com.flavio.android.petlegal.controll.ControlaUsuario;
import com.flavio.android.petlegal.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ListaUsuarios extends AppCompatActivity {

    ControlaUsuario cu;
    ControlaPessoa cp;
    ListView listaUsuariosListView;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_lista_usuarios );
        cu = new ControlaUsuario ( getApplicationContext () );
        cp = new ControlaPessoa ( getApplicationContext () );

        listaUsuariosListView = findViewById ( R.id.listaUsuarioListView  );


        listaUsuarios ();
        registerForContextMenu(listaUsuariosListView);

    }
    @Override
    protected void onResume() {
        super.onResume();
        listaUsuarios ();
    }
    public void listaUsuarios(){
        List<Usuario> usuarios = new ArrayList<Usuario> (  );

        usuarios = cu.listaUsuarios ();

        ArrayAdapter adapter  = new ArrayAdapter<Usuario> ( this, android.R.layout.simple_list_item_1, usuarios );

        listaUsuariosListView.setAdapter ( adapter );
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu ( menu, view, menuInfo );

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        usuario = (Usuario) listaUsuariosListView.getAdapter ().getItem ( info.position );

        //Ativar Usuario
        if(usuario.getStatus ()==0){
        MenuItem ativar = menu.add("Ativar Usuario");
        ativar.setOnMenuItemClickListener ( new MenuItem.OnMenuItemClickListener () {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                usuario.setStatus ( 1 );
                if(cu.atualizarUsuario ( usuario ))
                    sendMessage ( "Usuario ativado" );
                else{
                    sendMessage ( "Usuario não foi ativado" );}
                listaUsuarios ();
                return false;
            }
        } );}

        //Desativar usuario (Somente se estiver ativo e não for o administrador padrão do sistema)
        if(usuario.getStatus ()==1 && usuario.getCpf ()!=123456){
        MenuItem desativar = menu.add("Desativar Usuario");
        desativar.setOnMenuItemClickListener ( new MenuItem.OnMenuItemClickListener () {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                usuario.setStatus ( 0 );
                if(cu.atualizarUsuario ( usuario ))
                    sendMessage ( "Usuario desativado" );
                else{
                    sendMessage ( "Usuario não foi ativado" );}
                listaUsuarios ();
                return false;
            }
        } );}

        //Dar previlégio de Administrador
        if(usuario.getTipo ()!=1){
        MenuItem addAdmin = menu.add ( "Definir como Administrador" );
        addAdmin.setOnMenuItemClickListener ( new MenuItem.OnMenuItemClickListener () {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                usuario.setTipo ( 1 );
                if(cu.atualizarUsuario ( usuario ))
                    sendMessage ( "Usuario definido como administrador" );
                else{
                    sendMessage ( "Não foi possível definir como administrador" );}
                listaUsuarios ();
                return false;
            }
        } );}

        //Remover previlágio de Administrador
        if(usuario.getTipo ()==1 && usuario.getCpf ()!=123456){
        MenuItem remAdmin = menu.add("Remover previlégio de administrador");
        remAdmin.setOnMenuItemClickListener ( new MenuItem.OnMenuItemClickListener () {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                usuario.setTipo ( 3 );
                if(cu.atualizarUsuario ( usuario ))
                    sendMessage ( "Remivido previlégio de Administrador" );
                else{
                    sendMessage ( "Não foi possível remover previlégio de Administrador" );}
                listaUsuarios ();
                return false;
            }
        } );}



    }
    public void sendMessage(String texto ){
        Toast.makeText ( this, texto, Toast.LENGTH_SHORT ).show ();
    }
}
