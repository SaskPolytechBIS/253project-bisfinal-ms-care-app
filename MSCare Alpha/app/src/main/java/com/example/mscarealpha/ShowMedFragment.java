package com.example.mscarealpha;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ShowMedFragment extends DialogFragment {

    private MedTrack mMedTrack;

    @Override

    public Dialog onCreateDialog(Bundle savedInstance){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_show_med, null);

        TextView viewMed = dialogView.findViewById(R.id.viewMed);

        TextView viewDos = dialogView.findViewById(R.id.viewDos);

        TextView viewTimes = dialogView.findViewById(R.id.viewTimes);

        TextView viewFood = dialogView.findViewById(R.id.viewFood);

        TextView viewDrink = dialogView.findViewById(R.id.viewDrink);

        viewMed.setText(mMedTrack.getMed());

        viewDos.setText(mMedTrack.getDos());

        viewTimes.setText(mMedTrack.getTimes());

        viewFood.setText(mMedTrack.getFoods());

        viewDrink.setText(mMedTrack.getDrinks());

        Button btnOK = (Button) dialogView.findViewById(R.id.btnOK);

        builder.setView(dialogView).setMessage("Your Medicine");

        btnOK.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                dismiss();

            }

        });

        return builder.create();
    }

    public void sendNoteSelected(MedTrack medTrackSelected) {

        mMedTrack = medTrackSelected;

    }
}
