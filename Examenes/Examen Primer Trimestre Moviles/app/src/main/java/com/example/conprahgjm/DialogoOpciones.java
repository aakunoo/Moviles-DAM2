package com.example.conprahgjm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class DialogoOpciones extends DialogFragment{
    respuestaMyDialog respuesta;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Generamos un array para las opciones
        String [] opciones = {"Lista con datos", "Selector de fechas"};
        // Usamos la clase Builder para construir el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Escribimos el titulo
        builder.setTitle("Elija una opcion:");
        //Usamos el builder para crear la lista
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                respuesta.onRespuesta(i);
            }
        });
        return builder.create();
    }
    //Interfaz que se usara para recoger la respuesta
    public interface respuestaMyDialog {
        public void onRespuesta(int i);
    }
    // Se invoca cuando el fragmento se añade a la actividad
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        respuesta=(respuestaMyDialog)context;
    }
}

