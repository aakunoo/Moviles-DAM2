package com.example.prupac2vicvid;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

public class PreferenciasImagenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle estado) {
        super.onCreate(estado);
        setContentView(R.layout.activity_preferencias_imagen);
        if (estado == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedorPreferencias, new FragmentoPreferencias()).commit();
        }
    }

    public static class FragmentoPreferencias extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle estado, String rootKey) {
            setPreferencesFromResource(R.xml.preferencias, rootKey);
        }
    }
}
