package com.example.grid18botones;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaramos un arreglo de botones para los 18 botones
    private Button[] buttons = new Button[18];
    // Colores originales para los primeros 17 botones
    private int[] coloresOriginales = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA,
            Color.LTGRAY, Color.DKGRAY, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
            Color.CYAN, Color.MAGENTA, Color.LTGRAY, Color.DKGRAY, Color.RED};

    // Estado de los botones para saber si están en blanco o en su color original
    private boolean[] isBlanco = new boolean[18];  // Inicializa todo en falso (color original)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vinculamos el GridLayout desde el XML
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        // Creamos los 18 botones
        for (int i = 0; i < 18; i++) {
            buttons[i] = new Button(this);  // Creamos un nuevo botón
            buttons[i].setLayoutParams(new GridLayout.LayoutParams());  // Ajustamos el layout del botón

            // Si es el último botón, lo nombramos "RESET", sino, le damos un nombre "Botón X"
            if (i == 17) {
                buttons[i].setText("RESET");
                buttons[i].setBackgroundColor(Color.BLACK);  // El botón RESET tendrá color negro
                buttons[i].setTextColor(Color.WHITE);  // El texto del botón RESET será blanco
            } else {
                buttons[i].setText("Botón " + (i + 1));  // Nombres de Botón 1 a Botón 17
                buttons[i].setBackgroundColor(coloresOriginales[i]);  // Asignamos un color a cada botón
            }

            // Listener para los botones
            final int index = i;  // Usamos una variable final para referenciar dentro del listener
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (index == 17) {
                        // Si es el botón RESET, restauramos los colores originales y los estados
                        for (int j = 0; j < 17; j++) {
                            buttons[j].setBackgroundColor(coloresOriginales[j]);
                            isBlanco[j] = false;  // Restauramos el estado a "color original"
                        }
                    } else {
                        // Comprobar si el botón está en la columna central (índices 1, 4, 7, 10, 13, 16)
                        // o en las dos filas centrales (índices 6, 7, 8 y 9, 10, 11)
                        if (index == 1 || index == 4 || index == 7 || index == 10 || index == 13 || index == 16 ||
                                (index >= 6 && index <= 11)) {
                            // Alternar entre color blanco y color original
                            if (isBlanco[index]) {
                                buttons[index].setBackgroundColor(coloresOriginales[index]);  // Cambiar a color original
                            } else {
                                buttons[index].setBackgroundColor(Color.WHITE);  // Cambiar a blanco
                            }
                            isBlanco[index] = !isBlanco[index];  // Alternamos el estado del botón
                        }
                    }
                }
            });

            // Añadimos el botón al GridLayout
            gridLayout.addView(buttons[i]);
        }
    }
}
