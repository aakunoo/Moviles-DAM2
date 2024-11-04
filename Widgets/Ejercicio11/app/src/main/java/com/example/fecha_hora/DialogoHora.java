package com.example.fecha_hora;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DialogoHora extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    OnHoraSeleccionada f;
    @Override
    public void onAttach(Context context) {
        f=(OnHoraSeleccionada)context;
        super.onAttach(context);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c=Calendar.getInstance();
        int horas=c.get(Calendar.HOUR);
        int minutos=c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(),this,horas,minutos,true);
    }
    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i2) {
        GregorianCalendar g=new GregorianCalendar(0,0 ,0,i,i2);
        f.onResultadoHora(g); }
    public interface OnHoraSeleccionada{
        public void onResultadoHora(GregorianCalendar fecha); }
}
