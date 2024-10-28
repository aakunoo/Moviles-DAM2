package com.example.widgets;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    @SuppressLint({"MissingSuperCall", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[]
                elementos = {"León", "Zamora", "Salamanca", "Palencia", "Valladolid", "Ávila", "Burgos", "Segovia", "Soria"};
        ArrayAdapter<String> adaptador;
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ListView l = (ListView) findViewById(R.id.listView);
        adaptador = new ArrayAdapter<String>(this, R.layout.fichero, elementos);
        l.setAdapter(adaptador);
        l.setOnItemClickListener(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void onItemClick(AdapterView<?> a, View view, int position, long id){
        TextView t=(TextView)findViewById(R.id.textView3);
        t.setText("Has elegido: "+a.getItemAtPosition(position).toString());
    }
}