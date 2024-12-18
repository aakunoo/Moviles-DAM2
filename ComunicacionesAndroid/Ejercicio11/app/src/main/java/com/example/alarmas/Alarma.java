package com.example.alarmas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Alarma extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Mensaje emergente al activarse la alarma
        Toast.makeText(context, "DESPIERTAAAAAAAAAAAA", Toast.LENGTH_LONG).show();
    }
}
