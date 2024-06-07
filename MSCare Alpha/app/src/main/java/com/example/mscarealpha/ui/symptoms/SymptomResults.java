package com.example.mscarealpha.ui.symptoms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mscarealpha.R;

import java.util.List;

public class SymptomResults extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_symptom_results, container, false); // Use your XML layout with the TextView

        SymptomDbHelper dm = new SymptomDbHelper(getActivity());

        TextView textResults = v.findViewById(R.id.textResults); // Get the TextView

        List<Symptom> symptomList = dm.selectAll(); // Fetch the symptom data
        String list = ""; // Prepare a string to hold the formatted results

        // Loop through the symptom list (not the Cursor)
        for (Symptom symptom : symptomList) {
            list += (symptom.getBodyPart() + " - " + symptom.getSymptomName() + " (Pain: " + symptom.getPainLevel() + ")\n"); // Format the symptom data
        }

        textResults.setText(list); // Display the formatted data in the TextView
        return v;
    }
}
