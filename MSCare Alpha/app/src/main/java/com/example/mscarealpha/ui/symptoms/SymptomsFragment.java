package com.example.mscarealpha.ui.symptoms;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mscarealpha.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SymptomsFragment extends Fragment {


    Spinner bodyPartSpinner, symptomSpinner;
    private List<Symptom> symptomlist= new ArrayList<>(); // the datasource
    private SymptomAdapter mAdapter; // for the Recycler.ViewAdapter

    private RecyclerView recyclerView; // to reference the recyclerview UI Widget
    HashMap<String, List<String>> symptomMap; // Map body parts to symptoms

    // Array of strings for menu items (body parts)
    String[] bodyParts = {
            "Head", "Neck and Shoulder", "Chest", "Hand", "Stomach", "Groin",
            "Thigh and Upper Leg", "Lower Leg", "Upper Back", "Lower Back",
            "Back Head and Neck", "Lower Leg Back"
    };

    public static SymptomsFragment newInstance() {
        return new SymptomsFragment();
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_symptoms_and_journaling, container, false);

        // Getting references to the UI widgets
        Button btnLog = root.findViewById(R.id.logging_button);
        bodyPartSpinner = root.findViewById(R.id.bodypart_dropdown);
        symptomSpinner = root.findViewById(R.id.symptom_dropdown);
        SeekBar pain_range_bar = root.findViewById(R.id.pain_range_bar);
        TextView notes_and_comments = root.findViewById(R.id.notes_and_comments_textbox);


        // Initialize the symptom map (MS symptom data)
        symptomMap = new HashMap<>();

        // Head
        symptomMap.put("Head", Arrays.asList("Cognitive changes", "Headaches", "Dizziness", "Facial numbness", "Vision problems"));

        // Neck and Shoulder
        symptomMap.put("Neck and Shoulder", Arrays.asList("Pain and stiffness", "Lhermitte's sign", "Weakness"));

        // Chest
        symptomMap.put("Chest", Arrays.asList("Tightness or banding sensation (MS hug)", "Respiratory problems"));

        // Hand
        symptomMap.put("Hand", Arrays.asList("Numbness and tingling", "Weakness", "Tremors"));

        // Stomach
        symptomMap.put("Stomach", Arrays.asList("Bowel problems", "Pain or discomfort"));

        // Groin
        symptomMap.put("Groin", Arrays.asList("Sexual dysfunction", "Bladder problems"));

        // Thigh and Upper Leg
        symptomMap.put("Thigh and Upper Leg", Arrays.asList("Weakness", "Numbness and tingling", "Spasticity"));

        // Lower Leg
        symptomMap.put("Lower Leg", Arrays.asList("Weakness", "Numbness and tingling", "Foot drop"));

        // Upper Back
        symptomMap.put("Upper Back", Arrays.asList("Pain and stiffness", "Weakness"));

        // Lower Back
        symptomMap.put("Lower Back", Arrays.asList("Pain and stiffness", "Weakness"));

        // Back Head and Neck
        symptomMap.put("Back Head and Neck", Arrays.asList("Pain and stiffness", "Muscle spasms"));

        // Lower Leg Back
        symptomMap.put("Lower Leg Back", Arrays.asList("Pain and stiffness", "Muscle spasms"));

        ArrayAdapter<String> bodyPartAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, new ArrayList<>(symptomMap.keySet()));
        bodyPartAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bodyPartSpinner.setAdapter(bodyPartAdapter);

        bodyPartSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedBodyPart = parent.getItemAtPosition(position).toString();
                updateSymptomSpinner(selectedBodyPart);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Load the recycler view with some data in a loop


                // Save Symptom Data
                // 1. Get Selected Values from Spinners
                String selectedBodyPart = bodyPartSpinner.getSelectedItem().toString();
                String selectedSymptom = symptomSpinner.getSelectedItem().toString();

                // 2. Get Pain Level from SeekBar
                int painLevel = pain_range_bar.getProgress();

                // 3. Get Notes from EditText
                String notes = notes_and_comments.getText().toString();

                // 4. Get Current Date and Time
                //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                //String timestamp = dateFormat.format(new Date());
                // 4. Alternative: Get Timestamp
                long timestamp = System.currentTimeMillis();

                // Creating a symptom object and populating it with the collected data
                Symptom newSymptom = new Symptom(selectedBodyPart, selectedSymptom, painLevel, notes, timestamp);

                // adding the new Symptom to the data list
                symptomlist.add(newSymptom);

                // Create an instance of the Dialog Fragment
                symptomDialog myDialog = new symptomDialog();

                // show the dialog using this Activity's Fragment Manager
                myDialog.show(getActivity().getSupportFragmentManager(), "123");
            }
        });

        return root;
    }

    private void updateSymptomSpinner(String bodyPart) {
        List<String> symptoms = symptomMap.get(bodyPart);
        ArrayAdapter<String> symptomAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, symptoms);
        symptomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        symptomSpinner.setAdapter(symptomAdapter);
    }
}
