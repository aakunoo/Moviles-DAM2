package com.example.ejercicio3;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class DatosPersonales extends PreferenceFragment {

    @Override
    public void OnCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.datos_personales);
    }
}

