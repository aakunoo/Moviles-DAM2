package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

public class Receptor extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
            Toast.makeText(context, "SMS Received", Toast.LENGTH_LONG).show();
        if(intent.getAction().equals("android.intent.action.AIRPLANE_MODE"))
            Toast.makeText(context, "Modo avión cambiado", Toast.LENGTH_LONG).show();
        if(intent.getAction().equals("android.intent.action.ACTION_POWER_CONNECTED"))
            Toast.makeText(context, "Batería conectada", Toast.LENGTH_LONG).show();
        if(intent.getAction().equals("android.intent.action.ACTION_POWER_DISCONNECTED"))
            Toast.makeText(context, "Batería desconectada", Toast.LENGTH_LONG).show();
        if (intent.getAction().equals("android.intent.action.TIME_SET"))
            Toast.makeText(context, "Hora cambiada", Toast.LENGTH_LONG).show();
        if(intent.getAction().equals("android.intent.action.BATTERY_LOW"))
            Toast.makeText(context, "Batería baja", Toast.LENGTH_LONG).show();
    }

}

