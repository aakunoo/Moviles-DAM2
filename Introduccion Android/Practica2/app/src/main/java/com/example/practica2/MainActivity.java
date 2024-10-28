package com.example.practica2;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity{//implements OnClickListener

    // Declarar variables para el boton y para el cuadro de texto.
    // Declaro como quiero que se llame mi texto y boton
    TextView cuadroDeTexto;
    Button boton1, boton2;


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

        // Aqui enlazo mis nombres de la variable con la ID a la que quiero que pertenezcan
        cuadroDeTexto=findViewById(R.id.txt1);
        boton1=findViewById(R.id.bt1);
        boton2=findViewById(R.id.bt2);

        boton1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                boton1.setText(":)");
                cuadroDeTexto.setText("Bienvenido a DAM!");


            }
        }); //Con esto hacemos que en cualquier sitio de la clase donde se haga un click se ejecute lo de abajo.
        boton2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                boton2.setText("GG");
                cuadroDeTexto.setText("Suerte con el grado.");

            }
        });





        // La otra forma de realizar esto es yendo a la interfaz, y en la opcion de "onclick" poner el metodo nuevo "pulsar".
        /* OTRA FORMA
        public void pulsar(View view) {
            //que queremos que haga el boton cuando se pulse
            boton1.setText("Pulsado");
            cuadroDeTexto.setText("Guapetones");
         */


    }

    /*
    @Override
    public void onClick(View view) { // Para programar que queremos que haga el boton, ya que es el metodo "OnClick"
        boton1.setText("Pulsado");
        cuadroDeTexto.setText("Hola segunda prueba");
        boton2.setText("JEJEJEJJEJE");
      */

}
