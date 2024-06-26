package com.example.mscarealpha.ui.notes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.mscarealpha.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DialogNewNote extends DialogFragment {

    private Button btnSetReminder;
    private EditText editTitle;
    private EditText editDescription;
    private RadioButton radioButtonQuestions;
    private RadioButton radioButtonNotes;
    private RadioButton radioButtonTodo;
    private EditText txtDate;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_new_note, null);

        editTitle = dialogView.findViewById(R.id.editTitle);
        editDescription = dialogView.findViewById(R.id.editDescription);
        radioButtonQuestions = dialogView.findViewById(R.id.rbQuestions);
        radioButtonNotes = dialogView.findViewById(R.id.rbNotes);
        radioButtonTodo = dialogView.findViewById(R.id.rbTodo);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnOK = dialogView.findViewById(R.id.btnOK);
        btnSetReminder = dialogView.findViewById(R.id.btnSetReminder);

        txtDate = dialogView.findViewById(R.id.txtViewDate);

        String dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        //Set the formatted date to the TextView
        txtDate.setText(dateFormat);

        builder.setView(dialogView).setMessage("Add a new note");

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNote();
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

    private void createNote() {
        AppointmentNotes newNote = new AppointmentNotes();
        newNote.setTitle(editTitle.getText().toString());
        newNote.setDescription(editDescription.getText().toString());
        newNote.setQuestions(radioButtonQuestions.isChecked());
        newNote.setNotes(radioButtonNotes.isChecked());
        newNote.setTodo(radioButtonTodo.isChecked());
        newNote.setDate(txtDate.getText().toString());

        AppointmentNotesFragment callingActivity = (AppointmentNotesFragment) getParentFragment();
        callingActivity.createNewNote(newNote);
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
        // Reminder setting logic here
        Toast.makeText(getActivity(), "Reminder set for " + hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
    }


}