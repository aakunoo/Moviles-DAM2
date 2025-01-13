package com.example.preferencias2;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class Aficiones extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.aficiones);
    }
}
