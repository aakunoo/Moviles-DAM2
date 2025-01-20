package com.example.ejercicio8;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private TextView tvStatus;
    private boolean isPlayFirstTime = true;
    private boolean isPauseFirstTime = true;
    private boolean isStopFirstTime = true;

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

        tvStatus = findViewById(R.id.tvStatus);
        Button btnPlay = findViewById(R.id.btnPlay);
        Button btnPause = findViewById(R.id.btnPause);
        Button btnStop = findViewById(R.id.btnStop);


        mediaPlayer = MediaPlayer.create(this, R.raw.cancion); // Asegúrate de que el archivo MP3 esté en res/raw

        btnPlay.setOnClickListener(view -> {
            if (mediaPlayer != null) {
                if (isPlayFirstTime) {
                    tvStatus.setText("El reproductor está parado, lo pasamos a start()");
                    isPlayFirstTime = false;
                } else {
                    tvStatus.setText("Ya estás escuchando música");
                }
                mediaPlayer.start();
            }
        });

        btnPause.setOnClickListener(view -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                if (isPauseFirstTime) {
                    tvStatus.setText("El reproductor está reproduciendo, lo pausamos");
                    isPauseFirstTime = false;
                } else {
                    tvStatus.setText("La música está en pausa");
                }
                mediaPlayer.pause();
            }
        });

        btnStop.setOnClickListener(view -> {
            if (mediaPlayer != null) {
                if (isStopFirstTime) {
                    tvStatus.setText("El reproductor está reproduciendo, lo paramos");
                    isStopFirstTime = false;
                } else {
                    tvStatus.setText("La reproducción se ha detenido");
                }
                mediaPlayer.stop();
                mediaPlayer.prepareAsync();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
