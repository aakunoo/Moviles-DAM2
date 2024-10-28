package com.example.imagenyboton;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Variables para los elementos de la interfaz
    ImageButton imageButton;  // Botón con imagen
    ImageView imageView;  // Imagen que cambia

    // Controlar el cambio de imágenes (para alternar entre ellas)
    boolean toggle = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vinculamos los elementos de la interfaz (ImageButton y ImageView)
        imageButton = findViewById(R.id.imageButton2);  // Botón con imagen
        imageView = findViewById(R.id.imageView2);  // Imagen que cambia

        // Configuramos el listener para el botón de imagen
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambiar las imágenes cuando se pulse el botón
                if (toggle) {
                    imageButton.setImageResource(R.drawable.la_primera_muerte_de_krillin_1_);  // Cambia la imagen del botón
                    imageView.setImageResource(R.drawable.goky_ssj);
                              // Cambia la imagen del ImageView
                } else {
                    imageButton.setImageResource(R.drawable.file_1_);  // Cambia a otra imagen del botón
                    imageView.setImageResource(R.drawable.goku_base_1_);  // Cambia a otra imagen del ImageView
                }
                toggle = !toggle;  // Alternamos entre los estados
            }
        });
    }
}
