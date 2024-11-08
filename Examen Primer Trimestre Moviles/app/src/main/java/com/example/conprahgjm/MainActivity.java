package com.example.conprahgjm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements  DialogoOpciones.respuestaMyDialog, AdapterView.OnItemClickListener, DialogoFecha.OnFechaSeleccionada{
    //Arrays de donde cogeremos la información para rellenar la lista
    String[] animales = {"León", "Tigre", "Guepardo", "Lince", "Puma"};
    String[] cientifico = {"Panthera leo", "Panthera tigris", "Acinonyx jubatus", "Lynx", "Puma concolor"};
    int imagenes[] = {R.drawable.leones, R.drawable.tiger, R.drawable.guepardo, R.drawable.lince, R.drawable.puma};

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

        //Codigo para generar el dialogo nada más abrir la aplicación
        DialogoOpciones ds = new DialogoOpciones();
        ds.show(getSupportFragmentManager(), "Mi diálogo");
    }

    //Segun donde pulses te hara la opcion de la lista o la opcion de la fecha
    @Override
    public void onRespuesta(int i) {
        if (i == 0) {
            ListView selectorCiudades = findViewById(R.id.lvAnimales);
            AdaptadorPersonalizado a = new AdaptadorPersonalizado(this, R.layout.estilolista, animales);
            selectorCiudades.setAdapter(a);
            selectorCiudades.setOnItemClickListener(this);
        } else if (i == 1) {
            DialogoFecha d=new DialogoFecha();
            d.show(getSupportFragmentManager(),"Mi diálogoFecha");
        }
    }

    //Segun lo que elijas de la fecha eso sera introducido en el TextView
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView et1 = findViewById(R.id.textView2);
        et1.setText("Has elegido " + animales[i] + ", cuyo nombre científico es " + cientifico[i]);
    }

    //Segun la fecha que elijas se mostrara en el TextView
    @Override
    public void onResultadoFecha(GregorianCalendar fecha) {
        TextView et=findViewById(R.id.textView2);
        Calendar c = Calendar.getInstance();
        int año=c.get(Calendar.YEAR);
        int mes=c.get(Calendar.MONTH);
        int dia=c.get(Calendar.DAY_OF_MONTH);
        et.setText("Hoy es " + dia + "/" + mes + "/" + año  + " y se a elegido " + fecha.get(Calendar.DAY_OF_MONTH)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.YEAR));

    }

    //Adaptador personalizado para crear las filas personalizadas en las listas
    public class AdaptadorPersonalizado extends ArrayAdapter<String> {
        public AdaptadorPersonalizado(Context ctx, int txtViewResourceId, String[]objects) {
            super(ctx, txtViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View cnvtView, ViewGroup prnt) {
            return crearFilaPersonalizada(position, cnvtView, prnt);
        }

        @Override
        public View getView(int pos, View cnvtView, ViewGroup prnt) {
            return crearFilaPersonalizada(pos, cnvtView, prnt);
        }

        //Codigo para crear las filas personalizadas
        public View crearFilaPersonalizada(int position, View convertView,ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View miFila = inflater.inflate(R.layout.estilolista, parent, false);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView nombre = (TextView) miFila.findViewById(R.id.tvnombre);
            nombre.setText(animales[position]);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView descripcion = (TextView)miFila.findViewById(R.id.tvcientifico);
            descripcion.setText(cientifico[position]);
            ImageView imagen = (ImageView) miFila.findViewById(R.id.imageView2);
            imagen.setImageResource(imagenes[position]);
            return miFila;


        }
    }
}