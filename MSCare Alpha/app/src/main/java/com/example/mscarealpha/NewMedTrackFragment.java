package com.example.mscarealpha;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.mscarealpha.ui.medtrack.MedTrackFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NewMedTrackFragment extends DialogFragment {

//    EditText dp1;
//    Calendar calendar;

    @Override

    public Dialog onCreateDialog (Bundle savedInstanceState){
        // R.layout.my_layout - that's the layout where your textview is placed
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =getActivity().getLayoutInflater();
        View dialogView =inflater.inflate(R.layout.dialog_new_medtrack, null);
        final EditText editMed =dialogView.findViewById(R.id.editMed);
        final EditText editDosage = dialogView.findViewById(R.id.editDosage);
        final EditText editTimes = dialogView.findViewById(R.id.editTimes);
        final EditText editFoods = dialogView.findViewById(R.id.editFoods);
        final EditText editDrinks = dialogView.findViewById(R.id.editDrinks);



        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnSave = dialogView.findViewById(R.id.btnSave);

        builder.setView(dialogView).setMessage("Add new medication");

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {


                dismiss();

            }

        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new note

                MedTrack newMed = new MedTrack();

                // Set its variables to match the

                // user's entries on the form

                newMed.setMed(editMed.getText().toString());

                newMed.setDos(editDosage.getText().toString());

                newMed.setTimes(editTimes.getText().toString());

                newMed.setFoods(editFoods.getText().toString());

                newMed.setDrinks(editDrinks.getText().toString());

                MedTrackFragment callingActivity = (MedTrackFragment) getParentFragment();


                callingActivity.createNewMed(newMed);


                dismiss();
            }
        });




//        dp1 = view.findViewById(R.id.txtDate);
//        Calendar calendar = Calendar.getInstance();
//        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                calendar.set(Calendar.YEAR, year);
//                calendar.set(Calendar.MONTH, month);
//                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//
//                updateCalendar();
//            }
//
//            private void updateCalendar(){
//                String Format = "yyyy/MM/dd";
//                SimpleDateFormat sdf = new SimpleDateFormat(Format, Locale.US);
//
//                dp1.setText(sdf.format(calendar.getTime()));
//
//            }
//        };
//
//        dp1.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Context context = getActivity(); // Or use any appropriate context
//
//                // Create the DatePickerDialog with the correct parameters
//                new DatePickerDialog(context, date, calendar.get(Calendar.YEAR),
//                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
//            }
//        });
        return builder.create();
    }


}
