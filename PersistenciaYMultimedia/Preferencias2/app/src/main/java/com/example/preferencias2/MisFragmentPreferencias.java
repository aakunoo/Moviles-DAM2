package com.example.preferencias2;

import android.app.Activity;
import android.preference.PreferenceActivity;

import java.util.List;

public class MisFragmentPreferencias extends PreferenceActivity {
    @Override
    public void onBuildHeaders(List<PreferenceActivity.Header> target){
        super.onBuildHeaders(target);
        loadHeadersFromResource(R.xml.preferences_headers,target);
    }
    @Override
    protected boolean isValidFragment (String fragmentName) {
        if (Datos_Personales.class.getName().equals(fragmentName)) return true;
        return false;
    }

}
