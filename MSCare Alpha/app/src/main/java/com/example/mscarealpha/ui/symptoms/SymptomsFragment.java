package com.example.mscarealpha.ui.symptoms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mscarealpha.R;

public class SymptomsFragment extends Fragment {


    public static SymptomsFragment newInstance() {
        return new SymptomsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_symptoms_and_journaling, container, false);
    }

    }
