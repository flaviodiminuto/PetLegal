package com.flavio.android.petlegal.conectionfactory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Flávio on 29/05/2018.
 */

public class ConectionFactory extends SQLiteOpenHelper{

    private static final String nomeDatabase= "petlegal";
    private static final int versaoDatabase = 1;
    private static final String pathDatabase = "/data/user/0/com.flavio.android.petlegal/database"+nomeDatabase;
    private Context context;

    public ConectionFactory(Context context) {
        super ( context, nomeDatabase, null, versaoDatabase );
        this.context = context;
    }
    public void openDB(){
        if(!getWritableDatabase ().isOpen ()){
            this.context.openOrCreateDatabase ( pathDatabase ,context.MODE_ENABLE_WRITE_AHEAD_LOGGING,null );
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
