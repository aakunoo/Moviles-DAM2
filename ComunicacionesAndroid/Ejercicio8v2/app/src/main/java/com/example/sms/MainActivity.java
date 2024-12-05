package com.example.sms;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements receptorSMS.onRecibeSMS {

    private EditText et1, et2;
    private TextView tvrecibe;
    private Button boton;
    private receptorSMS receptorSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        tvrecibe = findViewById(R.id.tvrecibe);
        boton = findViewById(R.id.boton);

        // Solicitar permisos
        solicitarPermisos();

        // Configurar botón para enviar SMS
        boton.setOnClickListener(v -> enviarSMS());

        // Configurar receptor de SMS
        receptorSMS = new receptorSMS();
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(receptorSMS, filter);

        // Configurar listener
        receptorSMS.setOnRecibeSMSListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receptorSMS != null) {
            unregisterReceiver(receptorSMS);
        }
    }

    //  permisos necesarios
    private void solicitarPermisos() {
        String[] permisos = {
                Manifest.permission.SEND_SMS,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_PHONE_STATE
        };
        for (String permiso : permisos) {
            if (ContextCompat.checkSelfPermission(this, permiso) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, permisos, 0);
            }
        }
    }

    // Método para enviar SMS
    private void enviarSMS() {
        String numero = et1.getText().toString();
        String mensaje = et2.getText().toString();

        if (!numero.isEmpty() && !mensaje.isEmpty()) {
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(numero, null, mensaje, null, null);
                Toast.makeText(this, "SMS enviado correctamente", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "Error al enviar SMS", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para recibir SMS
    @Override
    public void onRecibeSMS(String origen, String mensaje) {
        tvrecibe.setText("Mensaje del número: " + origen + "\nMensaje: " + mensaje);
    }
}