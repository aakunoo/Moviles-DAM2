package com.example.eval1_vvjeronimo;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;

// Hecho por Jerónimo Vicente.

public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private Button btnJugar, btnVer;
    private Spinner spinner;
    private ImageView imagenGanaste;


    private int gridSize = 2;
    private boolean usarLetras = false;
    private ArrayList<String> gridValues;
    private int siguienteValor = 1;

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


        gridLayout = findViewById(R.id.gridLayout);
        btnJugar = findViewById(R.id.btnJugar);
        btnVer = findViewById(R.id.btnVer);
        spinner = findViewById(R.id.spinner);
        imagenGanaste = findViewById(R.id.imagenGanaste);

        imagenGanaste.setVisibility(View.GONE);


        btnJugar.setOnClickListener(v -> reiniciarJuego());
        btnVer.setOnClickListener(v -> showAllValuesTemporarily());

        reiniciarJuego();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_2x2) {
            gridSize = 2;
            reiniciarJuego();
            return true;
        } else if (id == R.id.menu_3x3) {
            gridSize = 3;
            reiniciarJuego();
            return true;
        } else if (id == R.id.menu_4x4) {
            gridSize = 4;
            reiniciarJuego();
            return true;
        } else if (id == R.id.menu_numeros) {
            usarLetras = false;
            reiniciarJuego();
            return true;
        } else if (id == R.id.menu_letras) {
            usarLetras = true;
            reiniciarJuego();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    private void reiniciarJuego() {
        siguienteValor = 1;
        imagenGanaste.setVisibility(View.GONE);

        iniciarlizarGrid();
        actualizarGrid();
    }

    private void iniciarlizarGrid() {
        gridValues = new ArrayList<>();
        int totalItems = gridSize * gridSize;

        if (usarLetras) {
            for (char i = 'A'; i < 'A' + totalItems; i++) {
                gridValues.add(String.valueOf(i));
            }
        } else {
            for (int i = 1; i <= totalItems; i++) {
                gridValues.add(String.valueOf(i));
            }
        }

        Collections.shuffle(gridValues);
    }

    private void actualizarGrid() {
        gridLayout.removeAllViews();
        gridLayout.setRowCount(gridSize);
        gridLayout.setColumnCount(gridSize);

        for (int i = 0; i < gridValues.size(); i++) {
            Button button = new Button(this);
            button.setText("");
            button.setTag(gridValues.get(i));

            button.setOnClickListener(v -> {
                String value = (String) v.getTag();
                if (value.equals(String.valueOf(siguienteValor))) {
                    button.setText(value);
                    siguienteValor++;
                    if (siguienteValor > gridValues.size()) {
                        imagenGanaste.setVisibility(View.VISIBLE);
                        Toast.makeText(this, "Has ganado", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Fallaste, empieza de nuevo.", Toast.LENGTH_SHORT).show();
                    reiniciarJuego();
                }
            });

            gridLayout.addView(button);
        }
    }

    private void showAllValuesTemporarily() {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Button button = (Button) gridLayout.getChildAt(i);
            button.setText((String) button.getTag());
        }


        new Handler().postDelayed(() -> {
            for (int i = 0; i < gridLayout.getChildCount(); i++) {
                Button button = (Button) gridLayout.getChildAt(i);
                button.setText("");
            }
        }, 1000);
    }
}
