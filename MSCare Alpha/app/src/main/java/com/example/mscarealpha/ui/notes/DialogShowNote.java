package com.example.mscarealpha.ui.notes;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.mscarealpha.R;


public class DialogShowNote extends DialogFragment {

    private AppointmentNotes mNote;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // All the other code goes here
        // All the other code goes here
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        LayoutInflater inflater =
                getActivity().getLayoutInflater();

        View dialogView =
                inflater.inflate(R.layout.dialog_show_note, null);

        TextView txtTitle =
                dialogView.findViewById(R.id.txtTitle);

        TextView txtDescription =
                dialogView.findViewById(R.id.txtDescription);

        txtTitle.setText(mNote.getTitle());
        txtDescription.setText(mNote.getDescription());

        TextView txtQuestions =
                dialogView.findViewById(R.id.textViewQuestions);

        TextView txtNotes =
                dialogView.findViewById(R.id.textViewNotes);

        TextView txtTodo =
                dialogView.findViewById(R.id.textViewTodo);

        if (!mNote.isQuestions()){
            txtQuestions.setVisibility(View.GONE);
        }

        if (!mNote.isNotes()){
            txtNotes.setVisibility(View.GONE);
        }

        if (!mNote.isTodo()){
            txtTodo.setVisibility(View.GONE);
        }

        Button btnOK = (Button) dialogView.findViewById(R.id.btnOK);

        builder.setView(dialogView).setMessage("Your Note");

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return builder.create();
    }


    // Receive a note from the MainActivity
    public void sendNoteSelected(AppointmentNotes noteSelected) {
        mNote = noteSelected;
    }

}
