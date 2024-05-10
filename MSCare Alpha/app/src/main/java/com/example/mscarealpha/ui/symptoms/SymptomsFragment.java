package com.example.mscarealpha.ui.symptoms;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mscarealpha.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SymptomsFragment extends Fragment {

    Spinner bodyPartSpinner, symptomSpinner;
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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_symptoms_and_journaling, container, false);

        bodyPartSpinner = root.findViewById(R.id.dropdown_menu);
        symptomSpinner = root.findViewById(R.id.symptom_menu);

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

        return root;
    }

    private void updateSymptomSpinner(String bodyPart) {
        List<String> symptoms = symptomMap.get(bodyPart);
        ArrayAdapter<String> symptomAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, symptoms);
        symptomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        symptomSpinner.setAdapter(symptomAdapter);
    }
}
