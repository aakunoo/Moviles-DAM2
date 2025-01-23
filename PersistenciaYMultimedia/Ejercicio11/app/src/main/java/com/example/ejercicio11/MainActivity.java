package com.example.ejercicio11;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private String rutaFotoActual;
    private static final int REQUEST_PERMISSIONS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonThumbnail = findViewById(R.id.button_thumbnail);
        Button botonFullsize = findViewById(R.id.button_fullsize);
        imageView = findViewById(R.id.image_thumbnail);

        botonThumbnail.setOnClickListener(v -> lanzarIntentCapturaThumbnail());
        botonFullsize.setOnClickListener(v -> lanzarIntentCapturaFullsize());

        solicitarPermisos();
    }

    private void solicitarPermisos() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, REQUEST_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permisos concedidos", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permisos denegados", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void lanzarIntentCapturaThumbnail() {
        Intent intentCaptura = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intentCaptura.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intentCaptura, SOLICITUD_CAPTURA_IMAGEN_THUMBNAIL);
        }
    }

    private void lanzarIntentCapturaFullsize() {
        Intent intentCaptura = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intentCaptura.resolveActivity(getPackageManager()) != null) {
            File archivoFoto = null;
            try {
                archivoFoto = crearArchivoImagen();
            } catch (IOException ex) {
                Toast.makeText(this, "Error al crear el archivo de imagen", Toast.LENGTH_SHORT).show();
            }
            if (archivoFoto != null) {
                Uri uriFoto = FileProvider.getUriForFile(this,
                        "com.example.ejercicio11.fileprovider",
                        archivoFoto);
                intentCaptura.putExtra(MediaStore.EXTRA_OUTPUT, uriFoto);
                startActivityForResult(intentCaptura, SOLICITUD_CAPTURA_IMAGEN_FULLSIZE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SOLICITUD_CAPTURA_IMAGEN_THUMBNAIL && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imagenBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imagenBitmap);
        } else if (requestCode == SOLICITUD_CAPTURA_IMAGEN_FULLSIZE && resultCode == RESULT_OK) {
            Toast.makeText(this, "Imagen guardada en: " + rutaFotoActual, Toast.LENGTH_LONG).show();
        }
    }

    private File crearArchivoImagen() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombreArchivoImagen = "JPEG_" + timeStamp + "_";
        File directorioAlmacenamiento = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen = File.createTempFile(
                nombreArchivoImagen,
                ".jpg",
                directorioAlmacenamiento
        );

        rutaFotoActual = imagen.getAbsolutePath();
        return imagen;
    }

    static final int SOLICITUD_CAPTURA_IMAGEN_THUMBNAIL = 1;
    static final int SOLICITUD_CAPTURA_IMAGEN_FULLSIZE = 2;
}