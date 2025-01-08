package com.example.ejercicio1_2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre, etApellido, etEdad;
    private TextView tvNombre, tvApellido, tvEdad;
    private Button btnCargarPreferencias, btnGuardarPreferencias; // Nuevo botón añadido

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

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etEdad = findViewById(R.id.etEdad);
        tvNombre = findViewById(R.id.tvNombre);
        tvApellido = findViewById(R.id.tvApellido);
        tvEdad = findViewById(R.id.tvEdad);
        btnCargarPreferencias = findViewById(R.id.btnCargarPreferencias);
        btnGuardarPreferencias = findViewById(R.id.btnGuardarPreferencias); // Asociamos el nuevo botón

        btnCargarPreferencias.setOnClickListener(v -> cargarPreferencias());


        btnGuardarPreferencias.setOnClickListener(v -> guardarPreferencias());
    }

    @Override
    protected void onStart() {
        super.onStart();
        cargarPreferencias();
    }

    @Override
    protected void onStop() {
        super.onStop();
        guardarPreferencias();
    }

    private void guardarPreferencias() {
        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellido.getText().toString().trim();
        String edadStr = etEdad.getText().toString().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || edadStr.isEmpty()) {
            return;
        }

        SharedPreferences prefs = getSharedPreferences("prefs_usuario", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("nombre", nombre);
        editor.putString("apellido", apellido);
        editor.putInt("edad", Integer.parseInt(edadStr));
        editor.apply();
    }

    private void cargarPreferencias() {
        SharedPreferences prefs = getSharedPreferences("prefs_usuario", MODE_PRIVATE);

        String nombre = prefs.getString("nombre", "Sin nombre");
        String apellido = prefs.getString("apellido", "Sin apellido");
        int edad = prefs.getInt("edad", 0);

        tvNombre.setText(nombre);
        tvApellido.setText(apellido);
        tvEdad.setText(String.valueOf(edad));
    }
}
