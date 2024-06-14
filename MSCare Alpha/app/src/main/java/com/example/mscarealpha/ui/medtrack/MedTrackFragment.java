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
//
//    public String SymptomsFinalTxt = "";

    public static MedTrackFragment newInstance() {
        return new MedTrackFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_medtrack, container, false);

        return view;
    }




}