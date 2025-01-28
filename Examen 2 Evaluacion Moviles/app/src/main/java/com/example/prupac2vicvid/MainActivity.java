package com.example.prupac2vicvid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView imagenFoto;
    ImageView imagenArtista;
    VideoView audioVideo;
    ActivityResultLauncher<Intent> lanzadorCamara;
    int [] canciones = {R.raw.una, R.raw.dos, R.raw.tres, R.raw.cuatro, R.raw.cinco};
    int indice;

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



        audioVideo = findViewById(R.id.audioVideo);
        imagenFoto = findViewById(R.id.imagenFoto);
        imagenArtista = findViewById(R.id.imagenArtista);
        indice = 0;
        cargarCancion();



        lanzadorCamara = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                resultado -> {
                    if (resultado.getResultCode() == RESULT_OK && resultado.getData() != null) {
                        Bitmap imagen = (Bitmap) resultado.getData().getExtras().get("data");
                        imagenFoto.setImageBitmap(imagen);
                    }
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferencias = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
        boolean mostrar = preferencias.getBoolean("pref_foto_artista", false);
        if (mostrar) {
            imagenArtista.setImageResource(R.drawable.cover);
            imagenArtista.setVisibility(android.view.View.VISIBLE);
        } else {
            imagenArtista.setVisibility(android.view.View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_capturar_imagen) {
            Intent intento = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intento.resolveActivity(getPackageManager()) != null) {
                lanzadorCamara.launch(intento);
            }
            return true;
        } else if (item.getItemId() == R.id.menu_preferencias) {
            Intent intento = new Intent(this, PreferenciasImagenActivity.class);
            startActivity(intento);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void cargarCancion() {
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + canciones[indice]);
        audioVideo.setVideoURI(uri);
        MediaController controlador = new MediaController(this);
        audioVideo.setMediaController(controlador);
        controlador.setMediaPlayer(audioVideo);
        audioVideo.requestFocus();
        audioVideo.start();
        audioVideo.setOnCompletionListener(mp -> {
            indice++;
            if (indice >= canciones.length) indice = 0;
            cargarCancion();
        });
    }
}
