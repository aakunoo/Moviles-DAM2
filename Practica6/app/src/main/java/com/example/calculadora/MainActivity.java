package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Declaramos las variables para los componentes de la interfaz (campos de texto, botones y texto de resultado)
    EditText editText1, editText2;  // Los dos números que el usuario introduce
    Button btnSuma, btnResta, btnMultiplica, btnDivide;  // Los botones para las operaciones
    TextView txtResultado;  // Donde se mostrará el resultado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);  // Habilita el modo Edge-to-Edge
        setContentView(R.layout.activity_main);  // Asigna el layout XML que hemos creado

        // Vinculamos los elementos del layout XML con las variables del código Java usando sus IDs
        editText1 = findViewById(R.id.editTextNumberDecimal);  // Primer número
        editText2 = findViewById(R.id.editTextNumberDecimal2);  // Segundo número
        btnSuma = findViewById(R.id.button);  // Botón de suma
        btnResta = findViewById(R.id.button2);  // Botón de resta
        btnMultiplica = findViewById(R.id.button3);  // Botón de multiplicación
        btnDivide = findViewById(R.id.button4);  // Botón de división
        txtResultado = findViewById(R.id.txtresult);  // Lugar donde se mostrará el resultado

        // Configuramos el botón de suma para que realice la operación de suma cuando se presiona
        btnSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Convertimos los valores de los EditText a números de tipo double
                double num1 = Double.parseDouble(editText1.getText().toString());
                double num2 = Double.parseDouble(editText2.getText().toString());

                // Realizamos la suma de los dos números
                double resultado = num1 + num2;

                // Mostramos el resultado en el TextView
                txtResultado.setText("El resultado de la suma de "+ num1 + " + " + num2 + " es "+ resultado);
            }
        });

        // Configuramos el botón de resta para que realice la operación de resta cuando se presiona
        btnResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Convertimos los valores de los EditText a números de tipo double
                double num1 = Double.parseDouble(editText1.getText().toString());
                double num2 = Double.parseDouble(editText2.getText().toString());

                // Realizamos la resta de los dos números
                double resultado = num1 - num2;

                // Mostramos el resultado en el TextView
                txtResultado.setText("El resultado de la resta entre "+ num1 +" - "+ num2 +" es " + resultado);
            }
        });

        // Configuramos el botón de multiplicación para que realice la operación de multiplicación cuando se presiona
        btnMultiplica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Convertimos los valores de los EditText a números de tipo double
                double num1 = Double.parseDouble(editText1.getText().toString());
                double num2 = Double.parseDouble(editText2.getText().toString());

                // Realizamos la multiplicación de los dos números
                double resultado = num1 * num2;

                // Mostramos el resultado en el TextView
                txtResultado.setText("Resultado: " + resultado);
            }
        });

        // Configuramos el botón de división para que realice la operación de división cuando se presiona
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Convertimos los valores de los EditText a números de tipo double
                double num1 = Double.parseDouble(editText1.getText().toString());
                double num2 = Double.parseDouble(editText2.getText().toString());

                // Verificamos si el segundo número es diferente de 0 (para evitar la división por 0)
                if (num2 != 0) {
                    // Realizamos la división
                    double resultado = num1 / num2;

                    // Mostramos el resultado en el TextView
                    txtResultado.setText("Resultado: " + resultado);
                } else {
                    // Si el segundo número es 0, mostramos un mensaje de error
                    txtResultado.setText("Error: División por cero");
                }
            }
        });

        // Mantener el comportamiento para los insets de la ventana (esto se refiere a la adaptación visual del contenido)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
