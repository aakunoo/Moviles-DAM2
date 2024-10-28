package com.example.radioycheck;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaramos las variables para los elementos de la interfaz
    EditText editText;  // Donde se muestra el texto que cambia
    RadioGroup radioGroupColorFondo, radioGroupColorFuente;  // RadioGroups para color de fondo y color de fuente
    CheckBox checkBoxNegrita, checkBoxCursiva, checkBoxMonospace, checkBoxSerif;  // Checkboxes para el tipo de letra

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vinculamos los elementos del layout con las variables de Java
        editText = findViewById(R.id.editTextText);  // El texto que va a cambiar
        radioGroupColorFondo = findViewById(R.id.radioGroup);  // RadioGroup de color de fondo
        radioGroupColorFuente = findViewById(R.id.radioGroup2);  // RadioGroup de color de fuente (Corregido con ID diferente)
        checkBoxNegrita = findViewById(R.id.checkBox);  // CheckBox para negrita
        checkBoxCursiva = findViewById(R.id.checkBox5);  // CheckBox para cursiva
        checkBoxMonospace = findViewById(R.id.checkBox3);  // CheckBox para Monospace
        checkBoxSerif = findViewById(R.id.checkBox4);  // CheckBox para Serif

        // Cambia el color de fondo según el botón de radio seleccionado
        radioGroupColorFondo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Usamos if-else para comprobar qué botón de radio fue seleccionado
                if (checkedId == R.id.radioButton) {  // Fondo rojo
                    editText.setBackgroundColor(Color.RED);
                } else if (checkedId == R.id.radioButton2) {  // Fondo azul
                    editText.setBackgroundColor(Color.BLUE);
                } else if (checkedId == R.id.radioButton3) {  // Fondo verde
                    editText.setBackgroundColor(Color.GREEN);
                } else if (checkedId == R.id.radioButton4) {  // Fondo blanco
                    editText.setBackgroundColor(Color.WHITE);
                } else if (checkedId == R.id.radioButton5) {  // Fondo negro
                    editText.setBackgroundColor(Color.BLACK);
                }
            }
        });

        // Cambia el color de la fuente según el botón de radio seleccionado
        radioGroupColorFuente.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Usamos if-else para verificar el color de la fuente
                if (checkedId == R.id.radioButton6) {  // Fuente roja
                    editText.setTextColor(Color.RED);
                } else if (checkedId == R.id.radioButton7) {  // Fuente azul
                    editText.setTextColor(Color.BLUE);
                } else if (checkedId == R.id.radioButton8) {  // Fuente verde
                    editText.setTextColor(Color.GREEN);
                } else if (checkedId == R.id.radioButton9) {  // Fuente blanca
                    editText.setTextColor(Color.WHITE);
                } else if (checkedId == R.id.radioButton10) {  // Fuente negra
                    editText.setTextColor(Color.BLACK);
                }
            }
        });

        // Cambia el tipo de letra según los checkboxes seleccionados
        CheckBox.OnCheckedChangeListener tipoDeLetraListener = (buttonView, isChecked) -> {
            // Estilo de letra: negrita y/o cursiva
            int estilo = Typeface.NORMAL;
            if (checkBoxNegrita.isChecked() && checkBoxCursiva.isChecked()) {
                estilo = Typeface.BOLD_ITALIC;
            } else if (checkBoxNegrita.isChecked()) {
                estilo = Typeface.BOLD;
            } else if (checkBoxCursiva.isChecked()) {
                estilo = Typeface.ITALIC;
            }

            // Cambiamos la familia de la fuente (monospace o serif)
            if (checkBoxMonospace.isChecked()) {
                editText.setTypeface(Typeface.MONOSPACE, estilo);
            } else if (checkBoxSerif.isChecked()) {
                editText.setTypeface(Typeface.SERIF, estilo);
            } else {
                editText.setTypeface(Typeface.SANS_SERIF, estilo);  // Fuente predeterminada
            }
        };

        // Aplicamos el listener de tipo de letra a los checkboxes
        checkBoxNegrita.setOnCheckedChangeListener(tipoDeLetraListener);
        checkBoxCursiva.setOnCheckedChangeListener(tipoDeLetraListener);
        checkBoxMonospace.setOnCheckedChangeListener(tipoDeLetraListener);
        checkBoxSerif.setOnCheckedChangeListener(tipoDeLetraListener);
    }
}
