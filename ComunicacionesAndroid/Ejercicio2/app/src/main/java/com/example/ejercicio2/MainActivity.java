package com.example.ejercicio2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etURL, etLatitud, etLongitud, etcorreo;


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

        etURL = findViewById(R.id.etURL);
        etLatitud = findViewById(R.id.etLatitud);
        etLongitud = findViewById(R.id.etLongitud);
        etcorreo = findViewById(R.id.etcorreo);
    }

    public void abrirURL(View view) {
        String url = etURL.getText().toString();
        if (!url.isEmpty()) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } else {
            Toast.makeText(this, "Por favor, introduce una URL válida.", Toast.LENGTH_SHORT).show();
        }
    }

    public void abrirMapa(View view) {
        String latitud = etLatitud.getText().toString();
        String longitud = etLongitud.getText().toString();

        if (!latitud.isEmpty() && !longitud.isEmpty()) {
            String geoUri = "geo:" + latitud + "," + longitud;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
            startActivity(intent);
        } else {
            Toast.makeText(this, "Por favor, introduce latitud y longitud.", Toast.LENGTH_SHORT).show();
        }
    }

    public void enviarCorreo(View view) {
        String correo = etcorreo.getText().toString();
        if (!correo.isEmpty()) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:" + correo)); // Configura la dirección de correo
            intent.putExtra(Intent.EXTRA_SUBJECT, "Asunto del correo"); // Asunto opcional
            intent.putExtra(Intent.EXTRA_TEXT, "Contenido del correo"); // Cuerpo del mensaje
            startActivity(intent);
        } else {
            Toast.makeText(this, "Por favor, introduce una dirección de correo.", Toast.LENGTH_SHORT).show();
        }
    }
}