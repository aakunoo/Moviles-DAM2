package com.example.conprahgjm;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DialogoFecha extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    OnFechaSeleccionada f;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c=Calendar.getInstance();
        int año=c.get(Calendar.YEAR);
        int mes=c.get(Calendar.MONTH);
        int dia=c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),this,año,dia,mes); }
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

        GregorianCalendar g=new GregorianCalendar(i,i1,i2);
        f.onResultadoFecha(g);
    }
    @Override
    public void onAttach(Context context) {
        f=(OnFechaSeleccionada)context;
        super.onAttach(context);
    }
    public interface OnFechaSeleccionada{
        public void onResultadoFecha(GregorianCalendar fecha);
    }


}



