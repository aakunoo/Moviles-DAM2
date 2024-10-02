package com.example.primosentredosnumeros;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Variables para los componentes de la interfaz
    EditText editTextNumberInicial, editTextNumberFinal;
    Button btBuscar;
    TextView txtPrimos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los componentes del layout
        editTextNumberInicial = findViewById(R.id.editTextNumber);  // Primer número
        editTextNumberFinal = findViewById(R.id.editTextNumber2);  // Segundo número
        btBuscar = findViewById(R.id.bt1);  // Botón para buscar primos
        txtPrimos = findViewById(R.id.txtprimos);  // Donde mostramos los primos
        txtPrimos.setMovementMethod(new ScrollingMovementMethod());
        // Configuramos el botón para buscar los números primos
        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Limpiamos el TextView
                txtPrimos.setText("");

                // Obtenemos los valores de los campos de texto
                int numInicial = Integer.parseInt(editTextNumberInicial.getText().toString());
                int numFinal = Integer.parseInt(editTextNumberFinal.getText().toString());

                // Mostramos los primos en el rango
                for (int i = numInicial; i <= numFinal; i++) {
                    if (esPrimo(i)) {
                        txtPrimos.append(i + "\n");  // Añadimos cada número primo al TextView
                    }
                }
            }
        });
    }

    // Método para comprobar si un número es primo (muy básico)
    private boolean esPrimo(int num) {
        if (num <= 1) return false;  // Los números menores que 2 no son primos
        for (int i = 2; i < num; i++) {  // Comprobamos si el número tiene algún divisor
            if (num % i == 0) {
                return false;  // Si tiene algún divisor, no es primo
            }
        }
        return true;  // Si no tiene divisores, es primo
    }
}
