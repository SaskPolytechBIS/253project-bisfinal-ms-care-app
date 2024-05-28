package com.example.mscarealpha.ui.medtrack;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.mscarealpha.R;

import java.util.Calendar;

public class NewMedTrackFragment extends DialogFragment {

    private EditText editMed;
    private EditText editDosage;
    private EditText editTimes;
    private EditText editFoods;
    private EditText editDrinks;
    private EditText editTextReminderMessage;
    private Button btnSetReminder;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_new_medtrack, null);

        editMed = dialogView.findViewById(R.id.editMed);
        editDosage = dialogView.findViewById(R.id.editDosage);
        editTimes = dialogView.findViewById(R.id.editTimes);
        editFoods = dialogView.findViewById(R.id.editFoods);
        editDrinks = dialogView.findViewById(R.id.editDrinks);
        editTextReminderMessage = dialogView.findViewById(R.id.editTextReminderMessage);
        btnSetReminder = dialogView.findViewById(R.id.btn_set_reminder);

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
                MedTrack newMed = new MedTrack();
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

        btnSetReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        return builder.create();
    }

    private void showTimePickerDialog() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                setReminder(hourOfDay, minute);
            }
        }, hour, minute, true);

        timePickerDialog.show();
    }

    private void setReminder(int hourOfDay, int minute) {
        String reminderMessage = editTextReminderMessage.getText().toString();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        try {
            AlarmManager alarmManager = (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(getActivity(), AlertReceiver.class);
            intent.putExtra("EXTRA_REMINDER_MESSAGE", reminderMessage);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

            if (alarmManager != null) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }

            Toast.makeText(getActivity(), "Reminder set for " + hourOfDay + ":" + formatMinute(minute), Toast.LENGTH_SHORT).show();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    private String formatMinute(int minute) {
        return minute < 10 ? "0" + minute : String.valueOf(minute);
    }
}
