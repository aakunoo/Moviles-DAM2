package com.example.preferencias2;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import androidx.annotation.Nullable;


public class Datos_Personales extends PreferenceFragment {
    @Override
    public void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        addPreferencesFromResource(R.xml.datos_personales);
    }
}
