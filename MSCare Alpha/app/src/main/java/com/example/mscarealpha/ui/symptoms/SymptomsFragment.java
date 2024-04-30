package com.example.mscarealpha.ui.symptoms;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mscarealpha.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SymptomsFragment extends Fragment implements View.OnClickListener {


    public static SymptomsFragment newInstance() {
        return new SymptomsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_symptoms_and_journaling, container, false);

        // Find your FABs
        FloatingActionButton headButton = root.findViewById(R.id.floatingActionButton_Head);
        FloatingActionButton neckButton = root.findViewById(R.id.floatingActionButton_Neck_and_Shoulder);
        FloatingActionButton chestButton = root.findViewById(R.id.floatingActionButton_Chest);
        FloatingActionButton handButton = root.findViewById(R.id.floatingActionButton_Hand);
        FloatingActionButton stomachButton = root.findViewById(R.id.floatingActionButton_Stomach);
        FloatingActionButton groinButton = root.findViewById(R.id.floatingActionButton_Groin);
        FloatingActionButton thighButton = root.findViewById(R.id.floatingActionButton_Thigh_and_UpperLeg);
        FloatingActionButton lowerLegButton = root.findViewById(R.id.floatingActionButton_LowerLeg);
        FloatingActionButton upperBackButton = root.findViewById(R.id.floatingActionButton_UpperBack);
        FloatingActionButton lowerBackButton = root.findViewById(R.id.floatingActionButton_LowerBack);
        FloatingActionButton backHeadNeckButton = root.findViewById(R.id.floatingActionButton_Back_head_and_Neck);
        FloatingActionButton lowerLegBackButton = root.findViewById(R.id.floatingActionButton_LowerLegBack);

        // Set click listeners
        // Set Click Listeners
        headButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSymptomDetailPage("head");
            }
        });
        neckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSymptomDetailPage("neck_and_shoulder");
            }
        });
        chestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSymptomDetailPage("chest");
            }
        });
        return root;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            /*case R.id.floatingActionButton_Head:
                openSymptomDetailPage("head");
                break;
            case R.id.floatingActionButton_Neck_and_Shoulder:
                openSymptomDetailPage("neck_and_shoulder");
                break;
            // ... add cases for other buttons */
        }
    }

    private void openSymptomDetailPage(String bodyPart) {
        /*Intent intent = new Intent(getActivity(), SymptomDetailActivity.class);
        intent.putExtra("bodyPart", bodyPart);
        startActivity(intent);*/
    }



    }
