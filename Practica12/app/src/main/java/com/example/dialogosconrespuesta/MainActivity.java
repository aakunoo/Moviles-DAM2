package com.example.dialogosconrespuesta;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonPregunta;
    private TextView textViewRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vinculamos los elementos de la interfaz
        buttonPregunta = findViewById(R.id.buttonPregunta);
        textViewRespuesta = findViewById(R.id.textViewRespuesta);

        // Listener para el botón que muestra el diálogo
        buttonPregunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoPregunta();
            }
        });
    }

    // Método para mostrar el diálogo
    private void mostrarDialogoPregunta() {
        // Crear el diálogo con dos botones: "Sí" y "No"
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pregunta muy importante:");
        builder.setMessage("¿Es un buen día?");

        // Configurar el botón "Sí"
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Mostrar respuesta en el TextView
                textViewRespuesta.setText("Es un buen día");
            }
        });

        // Configurar el botón "No"
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Mostrar respuesta en el TextView
                textViewRespuesta.setText("No es un buen día");
            }
        });

        // Mostrar el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
