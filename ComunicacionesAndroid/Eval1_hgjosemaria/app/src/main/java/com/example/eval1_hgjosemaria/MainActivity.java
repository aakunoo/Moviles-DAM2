package com.example.eval1_hgjosemaria;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.gridlayout.widget.GridLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String[] cuadro = { "2x2", "3x3", "4x4" };
    int [] imagenes = { R.drawable.sp2x2, R.drawable.sp3x3, R.drawable.sp4x4};
    int contador = 0;//Los usaremos para gestionar la pulsacion del spinner
    int contador1 = 0;
    int contador2 = 0;
    Button btnVer;
    ArrayList<String>letras = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O"));//Array para añdir letras al juego
    ArrayList<Integer>numeros = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16));//Array usado para los numeros
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
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Spinner selectortamaño = (Spinner) findViewById(R.id.spinner);
        AdaptadorPersonalizado a=new AdaptadorPersonalizado(this, R.layout.estilospiner, cuadro);
        selectortamaño.setAdapter(a);
        selectortamaño.setOnItemSelectedListener(this);
        añadeHijos();
        btnVer = findViewById(R.id.btnVer);
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GridLayout g = (GridLayout) findViewById(R.id.gridLayout);
                for (int i = 0; i < g.getChildCount(); i++) {
                    Button b  = (Button) g.getChildAt(i);
                    b.setText(b.getTag().toString());
                }
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        GridLayout g = (GridLayout) findViewById(R.id.gridLayout);
                        for (int i = 0; i < g.getChildCount(); i++) {
                            Button b = (Button) g.getChildAt(i);
                            b.setText("");
                        }
                    }
                },300);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    };
    //Creador de los hijos
    public void añadeHijos(){
        Random rm = new Random();
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int anchoPant = size.x/2;
        int altoPant = size.y/3;
        GridLayout g= (GridLayout) findViewById(R.id.gridLayout);
        Button b;
        int iden;
        for (int i=0; i<4; i++) {
            int aleatorio = rm.nextInt(4);
            b= new Button(this);
            b.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            iden=View.generateViewId();
            b.setId(iden);
            b.setTag(letras.get(i));
            g.addView(b,i);
        }
        g.getLayoutParams().width = anchoPant;
        g.getLayoutParams().height = altoPant;
    }

//Selector del spinner
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getItemIdAtPosition(i) == 0){
            contador1 = 0;
            contador2 = 0;
            contador++;
            if(contador == 1) {
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int anchoPant = size.x/2;
                int altoPant = size.y/3;
                GridLayout g= (GridLayout) findViewById(R.id.gridLayout);
                Button b;
                g.removeAllViews();
                g.setRowCount(2);
                g.setColumnCount(2);
                int ident;
                for (int j=0; j<4; j++) {
                    b= new Button(this);
                    b.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    ident=View.generateViewId();
                    b.setId(ident);
                    b.setTag(letras.get(j));
                    g.addView(b,j);
                }
                g.getLayoutParams().width = anchoPant;
                g.getLayoutParams().height = altoPant;

            }
        }
        if(adapterView.getItemIdAtPosition(i) == 1){
            contador1++;
            contador = 0;
            contador2 = 0;
            if(contador1 == 1) {
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int anchoPant = size.x;
                int altoPant = size.y/3;
                GridLayout g= (GridLayout) findViewById(R.id.gridLayout);
                Button b;
                g.removeAllViews();
                g.setRowCount(3);
                g.setColumnCount(3);
                int ident;
                for (int j=0; j<9; j++) {
                    b= new Button(this);
                    b.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    ident=View.generateViewId();
                    b.setId(ident);
                    b.setTag(letras.get(j));
                    g.addView(b,j);
                }
                g.getLayoutParams().width = anchoPant;
                g.getLayoutParams().height = altoPant;
            }
        }
        if(adapterView.getItemIdAtPosition(i) == 2){
            contador2++;
            contador = 0;
            contador1 = 0;
            if(contador2 == 1) {
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int anchoPant = size.x;
                int altoPant = size.y/3;
                GridLayout g= (GridLayout) findViewById(R.id.gridLayout);
                Button b;
                g.removeAllViews();
                g.setRowCount(4);
                g.setColumnCount(4);
                int ident;
                for (int j=0; j<16; j++) {
                    b= new Button(this);
                    b.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    ident=View.generateViewId();
                    b.setId(ident);
                    b.setTag(letras.get(j));
                    g.addView(b,j);

                }
                g.getLayoutParams().width = anchoPant;
                g.getLayoutParams().height = altoPant;
            }
        }
    }

    //Selector del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int iden= item.getItemId();
        if (iden == R.id.mnu2x2) {
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int anchoPant = size.x/2;
            int altoPant = size.y/3;
            GridLayout g= (GridLayout) findViewById(R.id.gridLayout);
            Button b;
            g.removeAllViews();
            g.setRowCount(2);
            g.setColumnCount(2);
            int ident;
            for (int i=0; i<4; i++) {
                b= new Button(this);
                b.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                ident=View.generateViewId();
                b.setId(ident);
                b.setTag(letras.get(i));
                g.addView(b,i);
            }
            g.getLayoutParams().width = anchoPant;
            g.getLayoutParams().height = altoPant;
            return true;
        } else if (iden == R.id.mnu3x3) {
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int anchoPant = size.x;
            int altoPant = size.y/3;
            GridLayout g= (GridLayout) findViewById(R.id.gridLayout);
            Button b;
            g.removeAllViews();
            g.setRowCount(3);
            g.setColumnCount(3);
            int ident;
            for (int i=0; i<9; i++) {
                b= new Button(this);
                b.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                ident=View.generateViewId();
                b.setId(ident);
                b.setTag(letras.get(i));
                g.addView(b,i);
            }
            g.getLayoutParams().width = anchoPant;
            g.getLayoutParams().height = altoPant;
            return true;
        } else if (iden == R.id.mnu4x4) {
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int anchoPant = size.x;
            int altoPant = size.y/3;
            GridLayout g= (GridLayout) findViewById(R.id.gridLayout);
            Button b;
            g.removeAllViews();
            g.setRowCount(4);
            g.setColumnCount(4);
            int ident;
            for (int i=0; i<16; i++) {
                b= new Button(this);
                b.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                ident=View.generateViewId();
                b.setId(ident);
                b.setTag(letras.get(i));
                g.addView(b,i);
            }
            g.getLayoutParams().width = anchoPant;
            g.getLayoutParams().height = altoPant;
            return true;
        } else if(iden == R.id.mnuLetras){
            item.setChecked(true);
            GridLayout g = (GridLayout) findViewById(R.id.gridLayout);
            for (int i = 0; i < g.getChildCount(); i++) {
                Button b  = (Button) g.getChildAt(i);
                b.setTag(letras.get(i));
            }
        }else if(iden == R.id.mnuNumeros){
            item.setChecked(true);
            GridLayout g = (GridLayout) findViewById(R.id.gridLayout);
            for (int i = 0; i < g.getChildCount(); i++) {
                Button b  = (Button) g.getChildAt(i);
                b.setTag(numeros.get(i).toString());
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public class AdaptadorPersonalizado extends ArrayAdapter<String> {
        public AdaptadorPersonalizado(Context ctx, int txtViewResourceId, String[]
                objects) {
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


    }
    public View crearFilaPersonalizada(int position, View convertView,
                                       ViewGroup parent) {
        LayoutInflater inflater = getLayoutInflater();
        View miFila = inflater.inflate(R.layout.estilospiner, parent,
                false);
        TextView cuadrotx = (TextView) miFila.findViewById(R.id.textView);
        cuadrotx.setText(cuadro[position]);
        ImageView imagen = (ImageView) miFila.findViewById(R.id.imageView);
        imagen.setImageResource(imagenes[position]);
        return miFila;
    }
}