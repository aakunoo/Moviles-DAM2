package com.example.ejercicio7;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private TextView textViewContent;
    private BufferedReader bf;

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

        textViewContent = findViewById(R.id.textViewContent);
        Button btnMostrar = findViewById(R.id.buttonMostrar);
        Button btnResetear = findViewById(R.id.buttonReset);

        Resources res = getResources();
        InputStream in = res.openRawResource(R.raw.fichero);
        bf = new BufferedReader(new InputStreamReader(in));

        btnMostrar.setOnClickListener(this::mostrarContenido);
        btnResetear.setOnClickListener(this::resetearTexto);
    }
    private void mostrarContenido(View view) {
        try {
            StringBuilder contenido = new StringBuilder();
            String linea;
            while ((linea = bf.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
            textViewContent.setText(contenido.toString());
            bf.close();
        } catch (IOException e) {
            textViewContent.setText("Error al leer el fichero.");
        }
    }
    private void resetearTexto(View view) {
        textViewContent.setText("");
        Resources res = getResources();
        InputStream in = res.openRawResource(R.raw.fichero);
        bf = new BufferedReader(new InputStreamReader(in));
    }
}
