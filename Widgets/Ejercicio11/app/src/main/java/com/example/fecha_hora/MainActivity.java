package com.example.fecha_hora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements DialogoFecha.OnFechaSeleccionada, DialogoHora.OnHoraSeleccionada {

    EditText et1,et2,et3;
    Button button3,button4;

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

        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
    }

    public void horaclick(View view){
        DialogoHora dialogo=new DialogoHora();
        dialogo.show(getSupportFragmentManager(),"hora");
    }

    public void fechaclick(View view){
        DialogoFecha dialogo=new DialogoFecha();
        dialogo.show(getSupportFragmentManager(),"fecha");
    }

    @Override
    public void onResultadoFecha(GregorianCalendar fecha) {
        EditText et=(EditText)findViewById(R.id.et2);
        et.setText(fecha.get(Calendar.DAY_OF_MONTH)+"/"+(fecha.get(Calendar.
                MONTH)+1)+"/"+fecha.get(Calendar.YEAR));
    }
    @Override
    public void onResultadoHora(GregorianCalendar hora) {
        EditText et=(EditText)findViewById(R.id.et3);
        et.setText(hora.get(Calendar.HOUR)+":"+(hora.get(Calendar.
                MINUTE)));
    }

}