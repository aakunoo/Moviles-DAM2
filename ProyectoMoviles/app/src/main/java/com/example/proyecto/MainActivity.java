package com.example.proyecto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView1, imageView2, imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);

        // URLs de las imágenes
        String url1 = "http://2.bp.blogspot.com/-oHFSibUxw0E/VmBkYkDWMyI/AAAAAAAAFZs/UGwI6Uh-VeY/s320/android1.png";
        String url2 = "https://1.bp.blogspot.com/-yKJmwIutOS4/YXWu1iVaIII/AAAAAAAAPHw/VBl_9RNP2_4rHTjEf5mHiKfF-d5R8xpkwCLcBGAsYHQ/s320/puesta.jpg";
        String url3 = "https://1.bp.blogspot.com/-iCRAMdzP0Ys/YXWu1pW41BI/AAAAAAAAPHs/mGiZABP24IUZlZ_i_Ln-ANcRxUlXdR-EgCLcBGAsYHQ/s320/gato.jpg";

        // Metodo 1: Cargar imagen con Picasso
        Picasso.get().load(url1).into(imageView1);

        // Metodo 2: Cargar imagen con Glide
        Glide.with(this).load(url2).into(imageView2);

        // Metodo 3: Cargar imagen con código puro

        new cargarImag(imageView3).execute(url3);
    }

    // Clase AsyncTask para carga manual de la imagen
    private static class cargarImag extends AsyncTask<String, Void, Bitmap> {
        private final ImageView imageView;

        public cargarImag(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap bm = null;
            try {
                URL url = new URL(urls[0]); // Crea una conexión con la URL
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("User-Agent", "Mozilla/5.0");
                connection.setDoInput(true);
                connection.connect(); // Abre la conexión
                InputStream input = connection.getInputStream();  // Obtiene los datos
                bm = BitmapFactory.decodeStream(input); // Convierte los datos en imagen
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bm;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                imageView.setImageBitmap(result); // Muestra la imagen en el ImageView
            } else {
                Toast.makeText(imageView.getContext(), "Error al cargar la imagen", Toast.LENGTH_SHORT).show();

            }
        }
    }
}

