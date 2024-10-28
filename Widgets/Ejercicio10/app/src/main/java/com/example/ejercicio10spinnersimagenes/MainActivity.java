package com.example.ejercicio10spinnersimagenes;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ejercicio10spinnersimagenes.R;

public class MainActivity extends AppCompatActivity {

    private TextView textViewSeleccion;
    private ListView listView;
    private String[] titles = {"León", "Zamora", "Salamanca"};
    private String[] descriptions = {"Provincia de Turismo", "Provincia de la Catedral", "Provincia de los Monumentos"};
    private int[] images = {R.drawable.alicante, R.drawable.zamora, R.drawable.salamanc};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        textViewSeleccion = findViewById(R.id.tw2);

        CustomAdapter adapter = new CustomAdapter();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textViewSeleccion.setText("Has elegido: " + titles[position]);
            }
        });
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
}
