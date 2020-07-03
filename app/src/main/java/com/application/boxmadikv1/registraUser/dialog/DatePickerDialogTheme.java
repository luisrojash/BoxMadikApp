package com.application.boxmadikv1.registraUser.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerDialogTheme extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                AlertDialog.THEME_HOLO_DARK,this,year,month,day);

        return datepickerdialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day){

      /*  TextView textview = (TextView)getActivity().findViewById(R.id.textView1);

        textview.setText(day + ":" + (month+1) + ":" + year);*/

    }

}
