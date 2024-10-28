package com.example.calculonumerosprimos;

import android.annotation.SuppressLint;
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

    TextView titulo1;
    TextView subtitulo1;
    EditText numtxt;
    Button boton1;
    TextView resultprimo;


    @SuppressLint("MissingInflatedId")
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

        titulo1 = findViewById(R.id.tx1);
        subtitulo1 = findViewById(R.id.tx2);
        resultprimo = findViewById(R.id.resultf);
        numtxt = findViewById(R.id.textnum1);
        boton1 = findViewById(R.id.bt1);

        boton1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                calculoPrimos();
            }
        });


    }

    public void calculoPrimos() {
        int n = Integer.parseInt(numtxt.getText().toString());  // Obtenemos el número ingresado
        int primoResult = encontrarEnesimoPrimo(n);  // Calculamos el N-ésimo primo
        resultprimo.setText(String.valueOf(primoResult));

}

    private int encontrarEnesimoPrimo(int n) {
        int count = 1;  // Contador de números primos encontrados
        int num = 1;    // Número que iremos comprobando

        while (count < n) {
            num++;
            if (esPrimo(num)) {
                count++;  // Si el número es primo, aumentamos el contador
            }
        }
        return num;  // Devolvemos el N-ésimo número primo
    }
    private boolean esPrimo(int num) {
        if (num <= 1) {
            return false;  // Los números menores que 2 no son primos
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;  // Si encontramos un divisor, no es primo
            }
        }
        return true;  // Si no encontramos divisores, es primo
    }
}
