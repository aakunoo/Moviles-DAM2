package com.example.ejercicio10spinnersimagenes;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ejercicio10spinnersimagenes.R;

public class MainActivity extends AppCompatActivity {

    private TextView textViewSeleccion;
    private ListView starks;
    private String[] titles = {"León", "Zamora", "Salamanca"};
    private String[] descriptions = {"Provincia de Turismo", "Provincia de la Catedral", "Provincia de los Monumentos"};
    private int[] images = {R.drawable.alicante, R.drawable.zamora, R.drawable.salamanc};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        starks = findViewById(R.id.listView);
        textViewSeleccion = findViewById(R.id.tw2);

        CustomAdapter adapter = new CustomAdapter();
        starks.setAdapter(adapter);

        starks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textViewSeleccion.setText("Has elegido: " + titles[position]);
            }
        });
        registerForContextMenu(starks);
    }

    private class CustomAdapter extends ArrayAdapter<String> {
        CustomAdapter() {
            super(MainActivity.this, R.layout.list_item, titles);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item, parent, false);
            }

            ImageView imageView = convertView.findViewById(R.id.item_image);
            TextView titleView = convertView.findViewById(R.id.item_title);
            TextView descriptionView = convertView.findViewById(R.id.item_description);

            imageView.setImageResource(images[position]);
            titleView.setText(titles[position]);
            descriptionView.setText(descriptions[position]);

            return convertView;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu_main, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        if (item.getItemId() == R.id.matar) {
    Toast.makeText(getApplicationContext(), "Hemos matado a " +
                    starks.getItemAtPosition(info.position),
            Toast.LENGTH_LONG).show();
    return true;
} else if (item.getItemId() == R.id.sanar) {
    Toast.makeText(getApplicationContext(), "Hemos sanado a " +
                    starks.getItemAtPosition(info.position),
            Toast.LENGTH_LONG).show();
    return true;
} else if (item.getItemId() == R.id.enviarmensjae) {
    Toast.makeText(getApplicationContext(), "Le hemos enviado un mensaje a "
            + starks.getItemAtPosition(info.position), Toast.LENGTH_LONG).show();
    return true;
} else {
    Toast.makeText(getApplicationContext(), "Le hemos hecho otra cosa a " +
                    starks.getItemAtPosition(info.position),
            Toast.LENGTH_LONG).show();
    return true;
}
        }
    }

