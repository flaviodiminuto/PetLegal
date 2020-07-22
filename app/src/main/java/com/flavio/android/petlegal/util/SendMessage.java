package com.flavio.android.petlegal.util;

import android.app.Activity;
import android.widget.Toast;

public class SendMessage {

    public static void toastShort(Activity activity, String texto){
        Toast.makeText ( activity,texto,Toast.LENGTH_SHORT).show ();
    }
    public static void toastLong(Activity activity, String texto){
        Toast.makeText ( activity,texto,Toast.LENGTH_SHORT).show ();
    }
}
