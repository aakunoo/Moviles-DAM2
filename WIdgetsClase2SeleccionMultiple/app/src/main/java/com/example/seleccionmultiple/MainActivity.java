package com.example.seleccionmultiple;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.SparseBooleanArray;
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
        adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,elementos);
        ListView l = (ListView) findViewById(R.id.listView);

        l.setAdapter(adaptador);
        l.setOnItemClickListener(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void onItemClick(AdapterView<?> a, View view, int position, long
            id){
        TextView t=(TextView)findViewById(R.id.textView3);
        ListView l=(ListView)findViewById(R.id.listView);
        StringBuffer seleccionado= new StringBuffer("Has elegido: ");
        SparseBooleanArray checked = l.getCheckedItemPositions();
        for(int i=0;i<checked.size();i++)
            if(checked.valueAt(i)){
                seleccionado.append(a.getItemAtPosition(checked.keyAt(i)).toString());
                seleccionado.append(", ");
            }
        t.setText(seleccionado.toString());
    }
}