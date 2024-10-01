package com.example.circunferencia;

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

    TextView radiotexto;
    EditText numradio;
    Button botoncalc;
    TextView txtlong;
    TextView txtarea;
    TextView txtvolum;
    TextView resultlong;
    TextView resultarea;
    TextView resultvolum;

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

    radiotexto = findViewById(R.id.txt1);
    numradio = findViewById(R.id.editTextNumber);
    botoncalc = findViewById(R.id.button);
    txtlong = findViewById(R.id.txt2);
    txtarea = findViewById(R.id.txt3);
    txtvolum = findViewById(R.id.txt4);
    resultlong = findViewById(R.id.resultlong);
    resultarea = findViewById(R.id.arearesult);
    resultvolum = findViewById(R.id.resultvolum);

    botoncalc.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            // Obtenemos el valor del radio ingresado por el usuario
            String cadenadenumerito = numradio.getText().toString();

            if (!cadenadenumerito.isEmpty()) {
                // Convertimos el valor a un número flotante (decimal)
                double radio = Double.parseDouble(cadenadenumerito);

                // Calculamos la longitud de la circunferencia, área del círculo y volumen de la esfera
                double longitud = 2 * Math.PI * radio;
                double area = Math.PI * Math.pow(radio, 2);
                double volumen = (4.0 / 3.0) * Math.PI * Math.pow(radio, 3);

                // Mostramos los resultados en los TextViews correspondientes
                resultlong.setText(String.format("%.2f", longitud));
                resultarea.setText(String.format("%.2f", area));
                resultvolum.setText(String.format("%.2f", volumen));
            }
        }
    });
    }
}