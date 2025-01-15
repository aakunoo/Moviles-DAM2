package com.example.ejercicio6;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

        Resources res = getResources();
        InputStream in = res.openRawResource(R.raw.lineas);
        bf = new BufferedReader(new InputStreamReader(in));

        Button btnMostrar = findViewById(R.id.buttonMostrar);
        btnMostrar.setOnClickListener(this::mostrar);
    }

    public void mostrar(View view) {
        try {
            String linea = bf.readLine();
            if (linea != null) {
                Toast.makeText(this, linea, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "FIN del fichero estático", Toast.LENGTH_SHORT).show();
                bf.close();
            }
        } catch (IOException e) {
            Toast.makeText(this, "Error al leer el fichero", Toast.LENGTH_SHORT).show();
        }
    }
}
