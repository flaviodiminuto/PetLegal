package com.flavio.android.petlegal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.util.Log;

import java.time.LocalDateTime;

public class TokenCrud extends Dao {
    public static final String TABLE_NAME = "credencial";
    private final String ID_FIELD = "id";
    private final String DATA_EMISSAO_FIELD = "data_emissao";
    private final String DATA_EXPIRACAO_FIELD = "data_expiracao";
    private final String PASSE_FIELD = "passe";
    public TokenCrud(Context context) {
        super(context);
    }

    public boolean criarTabela() {
        String nomeTabela = TABLE_NAME;
        String campos = "'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
                "'data_emissao' TEXT," +
                "'data_expiracao' TEXT," +
                "'passe' TEXT";
        return super.criarTabela(nomeTabela, campos);
    }

    public boolean persistToken(Credencial credential){
        ContentValues cv = new ContentValues();
        cv.put(DATA_EMISSAO_FIELD, notNull(credential.getData_emissao()));
        cv.put(DATA_EXPIRACAO_FIELD, notNull(credential.getData_expiracao()));
        cv.put(PASSE_FIELD, credential.getPasse());
        return inserir(TABLE_NAME,cv);
    }

    public Credencial getCredencial(){
        Log.i("Info","Obtendo Credencial");
        String query = "SELECT * FROM '"+TABLE_NAME+"' LIMIT 1";
        Cursor cursor = consulta(query);
        return cursorToPessoa(cursor);
    }

    private String notNull(Object o){
        return o != null ? o.toString() : "";
    }

    private Credencial cursorToPessoa(Cursor cursor){
        try{
            Credencial  credencial = new Credencial();
            credencial.setId((long) cursor.getInt(0));
            credencial.setData_emissao(stringToLocalDateTime(cursor.getString(1)));
            credencial.setData_expiracao(stringToLocalDateTime(cursor.getString(2)));
            credencial.setPasse(cursor.getString(3));
            return credencial;
        }catch (Exception e){
            Log.e("message_error", e.getLocalizedMessage());
        }
        return null;
    }

    private LocalDateTime stringToLocalDateTime(String dataString){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && !dataString.isEmpty())
                return LocalDateTime.parse(dataString);
        else
            return null;
    }
}
