package com.example.factorial;

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
    EditText numtxt;
    Button boton1;
    TextView resultfact;


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
        numtxt = findViewById(R.id.numerito);
        boton1 = findViewById(R.id.bt1);
        resultfact = findViewById(R.id.txtfactorial);

        boton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Factorizar();

            }
        });
    }

    public void Factorizar() {
        int numero = Integer.parseInt(numtxt.getText().toString());
        int resultf = 1;


        if (numero > 0) {
            for (int i = 1; i <= numero; i++) {
                resultf = resultf * i;

            }
        }
        resultfact.setText(String.valueOf(resultf));
    }
}