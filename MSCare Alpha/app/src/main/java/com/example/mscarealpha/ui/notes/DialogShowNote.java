package com.example.mscarealpha.ui.notes;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.mscarealpha.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


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

        TextView txtDate =
                dialogView.findViewById(R.id.txtViewDate);

        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

         //Set the formatted date to the TextView
        txtDate.setText(currentDate);

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
        Button btnEdit = (Button) dialogView.findViewById(R.id.btnEdit);


        builder.setView(dialogView).setMessage("Your Note");

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditNoteDialog();
            }
        });

        return builder.create();
    }

    private void showEditNoteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_note, null);

        EditText editTitle = dialogView.findViewById(R.id.editTitle);
        EditText editDescription = dialogView.findViewById(R.id.editDescription);

        editTitle.setText(mNote.getTitle());
        editDescription.setText(mNote.getDescription());

        builder.setView(dialogView)
                .setTitle("Edit Note")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mNote.setTitle(editTitle.getText().toString());
                        mNote.setDescription(editDescription.getText().toString());

                        // Update the note details in the original dialog
                        TextView txtTitle = getDialog().findViewById(R.id.txtTitle);
                        TextView txtDescription = getDialog().findViewById(R.id.txtDescription);
                        //TextView txtDate = getDialog().findViewById(R.id.txtViewDate);

                        txtTitle.setText(mNote.getTitle());
                        txtDescription.setText(mNote.getDescription());

                        // You can also save the updated note to your storage (e.g., database, file)
                        // Implement the actual save logic here

                        dismiss();
                    }
                })
                .setNegativeButton("Cancel", null);

        builder.create().show();
    }


    // Receive a note from the MainActivity
    public void sendNoteSelected(AppointmentNotes noteSelected) {
        mNote = noteSelected;
    }

}



