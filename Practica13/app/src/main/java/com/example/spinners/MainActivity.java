package com.example.spinners;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String[] elementos = new String[]{"León", "Zamora", "Salamanca", "Palencia", "Valladolid", "avila", "Burgos",
                "Segovia", "Soria"};



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adaptador;
        Spinner sp = findViewById(R.id.spinner);
        adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, elementos);


        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adaptador);
        sp.setOnItemSelectedListener(this);


        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView t =findViewById(R.id.holi);
        Spinner sp = (Spinner) findViewById(R.id.spinner);
        t.setText(sp.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        TextView t=(TextView)findViewById(R.id.holi);
        t.setText("No se ha seleccionado nada");

    }
}