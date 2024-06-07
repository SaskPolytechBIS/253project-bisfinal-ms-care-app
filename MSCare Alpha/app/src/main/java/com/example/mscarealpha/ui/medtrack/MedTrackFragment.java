package com.example.mscarealpha.ui.medtrack;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mscarealpha.R;
import com.example.mscarealpha.ui.symptoms.Symptom;
import com.example.mscarealpha.ui.symptoms.SymptomDbHelper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MedTrackFragment extends Fragment {

    public String SymptomsFinalTxt = "";

    public static MedTrackFragment newInstance() {
        return new MedTrackFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_medtrack, container, false);

        // Printing the results into the text View
        SymptomDbHelper dm = new SymptomDbHelper(getActivity());

        TextView textResults = view.findViewById(R.id.results_textView); // Get the TextView

        List<Symptom> symptomList = dm.selectAll(); // Fetch the symptom data
        String list = ""; // Prepare a string to hold the formatted results

        // Loop through the symptom list (not the Cursor)
        for (Symptom symptom : symptomList) {
            list += ("Body Part Associated : " + symptom.getBodyPart() + " \nSymptom Type: " + symptom.getSymptomName() + " \nPain: " + symptom.getPainLevel() + "\n"); // Formatting the symptom data
        }

        textResults.setText(list); // Display the formatted data in the TextView

        SymptomsFinalTxt = list;

        Button btnBack = view.findViewById(R.id.btn_SymptomFrag); // Find the back button (add this to your XML)
        Button btnSave = view.findViewById(R.id.btn_Save); // Find the save button (add this to your XML)

        btnBack.setOnClickListener(v -> getParentFragmentManager().popBackStack()); // Go back
        btnSave.setOnClickListener(v -> saveResultsToFile()); // Save results


        return view;
    }

    private void saveResultsToFile() {

        try {
            // Create a file name
            String fileName = "symptom_results_" + System.currentTimeMillis() + ".txt";

            // Get the directory to save in (Downloads)
            File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

            // Create the file
            File file = new File(downloadsDir, fileName);
            FileWriter writer = new FileWriter(file);
            writer.write(SymptomsFinalTxt);
            writer.close();

            Toast.makeText(requireContext(), "Results saved to Downloads folder", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Error saving results", Toast.LENGTH_SHORT).show();
        }
    }


}