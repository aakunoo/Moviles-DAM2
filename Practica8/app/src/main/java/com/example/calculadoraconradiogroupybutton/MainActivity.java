package com.example.calculadoraconradiogroupybutton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaramos las variables para los componentes de la interfaz
    EditText editTextNumber1, editTextNumber2;  // Los campos de texto para ingresar los números
    RadioGroup radioGroup;  // Grupo de botones de radio para seleccionar la operación
    RadioButton radioButtonSuma, radioButtonResta, radioButtonMultiplicacion, radioButtonDivision;  // Botones de radio
    TextView txtResultado;  // Donde se mostrará el resultado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vinculamos los elementos de la interfaz con las variables en Java
        editTextNumber1 = findViewById(R.id.editTextNumber);  // Primer número
        editTextNumber2 = findViewById(R.id.editTextNumber2);  // Segundo número
        radioGroup = findViewById(R.id.gruporadio);  // Grupo de botones de radio
        radioButtonSuma = findViewById(R.id.radioButton2);  // Botón para suma
        radioButtonResta = findViewById(R.id.radioButton4);  // Botón para resta
        radioButtonMultiplicacion = findViewById(R.id.radioButton3);  // Botón para multiplicación
        radioButtonDivision = findViewById(R.id.radioButton);  // Botón para división
        txtResultado = findViewById(R.id.txtresult);  // Texto donde mostramos el resultado

        // Configuramos lo que pasa cuando el usuario selecciona una operación y presiona el botón
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Primero obtenemos los números que el usuario introdujo
                String numero1Str = editTextNumber1.getText().toString();  // Obtenemos el texto del primer número
                String numero2Str = editTextNumber2.getText().toString();  // Obtenemos el texto del segundo número

                // Verificamos que los campos no estén vacíos
                if (!numero1Str.isEmpty() && !numero2Str.isEmpty()) {
                    // Convertimos los textos a números de tipo double
                    double numero1 = Double.parseDouble(numero1Str);  // Convertimos el primer número
                    double numero2 = Double.parseDouble(numero2Str);  // Convertimos el segundo número
                    double resultado = 0;  // Aquí guardaremos el resultado de la operación

                    // Si el botón de suma está marcado, hacemos la suma
                    if (radioButtonSuma.isChecked()) {
                        resultado = numero1 + numero2;  // Suma de los dos números
                        txtResultado.setText("Resultado: " + resultado);  // Mostramos el resultado en el TextView
                    }
                    // Si el botón de resta está marcado, hacemos la resta
                    else if (radioButtonResta.isChecked()) {
                        resultado = numero1 - numero2;  // Resta de los dos números
                        txtResultado.setText("Resultado: " + resultado);
                    }
                    // Si el botón de multiplicación está marcado, hacemos la multiplicación
                    else if (radioButtonMultiplicacion.isChecked()) {
                        resultado = numero1 * numero2;  // Multiplicación de los dos números
                        txtResultado.setText("Resultado: " + resultado);
                    }
                    // Si el botón de división está marcado, hacemos la división
                    else if (radioButtonDivision.isChecked()) {
                        // Primero verificamos que el segundo número no sea 0 para evitar división por cero
                        if (numero2 != 0) {
                            resultado = numero1 / numero2;  // División de los dos números
                            txtResultado.setText("Resultado: " + resultado);
                        } else {
                            // Si el segundo número es 0, mostramos un mensaje de error
                            txtResultado.setText("Error: No se puede dividir por cero");
                        }
                    }
                } else {
                    // Si los campos están vacíos, mostramos un mensaje para que el usuario sepa que debe llenar los campos
                    Toast.makeText(MainActivity.this, "Por favor, introduce ambos números", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
