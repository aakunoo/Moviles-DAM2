package com.example.ejercicio9;

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

    private VideoView videoView;
    private MediaController mediaController;
    private int currentSongIndex = 0;
    private int[] songResources = {
            R.raw.cancion1,
            R.raw.cancion2,
            R.raw.cancion3
    };

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

        videoView = findViewById(R.id.videoView);
        mediaController = new MediaController(this);

        setupMediaController();
        playSong(currentSongIndex);
    }

    private void setupMediaController() {
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.setOnCompletionListener(mp -> {
            currentSongIndex = (currentSongIndex + 1) % songResources.length;
            playSong(currentSongIndex);
        });
    }

    private void playSong(int index) {
        Uri songUri = Uri.parse("android.resource://" + getPackageName() + "/" + songResources[index]);
        videoView.setVideoURI(songUri);
        videoView.start();
    }
}
