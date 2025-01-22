package com.example.ejercicio10;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private VideoView vistaVideo;
    private MediaController controladorMedios;
    private int indiceVideoActual = 0;
    private int[] listaVideos = {
            R.raw.video,
            R.raw.video2
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets bordesSistema = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bordesSistema.left, bordesSistema.top, bordesSistema.right, bordesSistema.bottom);
            return insets;
        });

        vistaVideo = findViewById(R.id.videoView);
        controladorMedios = new MediaController(this);

        configurarReproductor();
    }

    private void configurarReproductor() {
        controladorMedios.setAnchorView(vistaVideo);
        vistaVideo.setMediaController(controladorMedios);

        reproducirVideo(indiceVideoActual);

        controladorMedios.setPrevNextListeners(
                v -> reproducirSiguienteVideo(),
                v -> reproducirVideoAnterior()
        );

        // pasa siguiente video
        vistaVideo.setOnCompletionListener(mp -> reproducirSiguienteVideo());
    }

    private void reproducirVideo(int indice) {
        Uri uriVideo = Uri.parse("android.resource://" + getPackageName() + "/" + listaVideos[indice]);
        vistaVideo.setVideoURI(uriVideo);
        vistaVideo.start();
    }

    private void reproducirSiguienteVideo() {
        indiceVideoActual = (indiceVideoActual + 1) % listaVideos.length; // para pasar al siguiente video
        reproducirVideo(indiceVideoActual);
    }

    private void reproducirVideoAnterior() {
        indiceVideoActual = (indiceVideoActual - 1 + listaVideos.length) % listaVideos.length; // para ir al video anterior
        reproducirVideo(indiceVideoActual);
    }

    @Override
    public boolean onTouchEvent(android.view.MotionEvent evento) {
        controladorMedios.show();
        return false;
    }
}
