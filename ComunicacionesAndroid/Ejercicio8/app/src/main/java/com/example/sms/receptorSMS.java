package com.example.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class receptorSMS extends BroadcastReceiver {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private onRecibeSMS respuesta;

    public void setOnRecibeSMSListener(onRecibeSMS listener) {
        this.respuesta = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVED)) {
            Bundle bundle = intent.getExtras();
            SmsMessage[] mensajes = null;
            String origen = null;
            StringBuilder contenido = new StringBuilder();

            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                mensajes = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    String format = bundle.getString("format");
                    mensajes[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                    origen = mensajes[i].getOriginatingAddress();
                    contenido.append(mensajes[i].getMessageBody());
                }
                if (respuesta != null) {
                    respuesta.onRecibeSMS(origen, contenido.toString());
                }
                Toast.makeText(context, "SMS Recibido", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Interfaz para comunicar el receptor con la actividad
    public interface onRecibeSMS {
        void onRecibeSMS(String origen, String mensaje);
    }
}
