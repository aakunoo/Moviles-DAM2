package com.example.preferencias2;

import android.preference.PreferenceActivity;

import java.util.List;

public class MisFragmentPreferencias extends PreferenceActivity {
    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.preferences_headers, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return Datos_Personales.class.getName().equals(fragmentName) ||
                HeroesFavoritos.class.getName().equals(fragmentName) ||
                Aficiones.class.getName().equals(fragmentName) ||
                Mascotas.class.getName().equals(fragmentName);
    }
}
