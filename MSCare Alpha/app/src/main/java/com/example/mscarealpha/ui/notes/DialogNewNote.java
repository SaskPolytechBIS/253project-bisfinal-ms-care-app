package com.example.mscarealpha.ui.notes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.DialogFragment;

import com.example.mscarealpha.R;

public class DialogNewNote extends DialogFragment {

    @Override

    public Dialog onCreateDialog (Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_new_note, null);

        final EditText editTitle = dialogView.findViewById(R.id.editTitle);
        final EditText editDescription = dialogView.findViewById(R.id.editDescription);
        final RadioButton radioButtonQuestions = dialogView.findViewById(R.id.rbQuestions);
        final RadioButton radioButtonNotes = dialogView.findViewById(R.id.rbNotes);
        final RadioButton radioButtonTodo = dialogView.findViewById(R.id.rbTodo);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnOK = dialogView.findViewById(R.id.btnOK);

        builder.setView(dialogView).setMessage("Add a new note");

        // Handle the cancel button
        btnCancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Create a new note
                AppointmentNotes newNote = new AppointmentNotes();

                // Set its variables to match the
                // user's entries on the form
                newNote.setTitle(editTitle.
                        getText().toString());

                newNote.setDescription(editDescription.
                        getText().toString());

                newNote.setQuestions(radioButtonQuestions.isChecked());
                newNote.setNotes(radioButtonNotes.isChecked());
                newNote.setTodo(radioButtonTodo.isChecked());


                // Get a reference to MainActivity
                AppointmentNotesFragment callingActivity = (AppointmentNotesFragment) getParentFragment();

                // Pass newNote back to MainActivity
                callingActivity.createNewNote(newNote);

                // Quit the dialog
                dismiss();
            }
        });

        return builder.create();



    }
}