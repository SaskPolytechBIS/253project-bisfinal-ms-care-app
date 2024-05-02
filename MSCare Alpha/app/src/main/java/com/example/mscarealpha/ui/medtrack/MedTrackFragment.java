package com.example.mscarealpha.ui.medtrack;


import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.example.mscarealpha.JSONSerializer;
import com.example.mscarealpha.MedTrack;
import com.example.mscarealpha.MedTrackAdapter;
import com.example.mscarealpha.NewMedTrackFragment;
import com.example.mscarealpha.R;
import com.example.mscarealpha.ShowMedFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MedTrackFragment extends Fragment{

    private JSONSerializer mSerializer;
    private List<MedTrack> medTrackList = new ArrayList<>();

    private RecyclerView recyclerView;

    private MedTrackAdapter mAdapter;

    private SharedPreferences mPrefs;


    public static MedTrackFragment newInstance() {
        return new MedTrackFragment();
    }

    OvershootInterpolator interpolator = new OvershootInterpolator();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medtrack, container, false);

        FloatingActionButton fabAdd, fabDel, fabOpenClose;

        fabDel = view.findViewById(R.id.fab_clear_all);

        fabOpenClose = view.findViewById(R.id.fab_open_close);

        fabAdd = view.findViewById(R.id.fab_add_medtrack);

        Float translationYaxis = 100f;

        final Boolean[] menuOpen = {false};

        fabDel.setAlpha(0f);
        fabAdd.setAlpha(0f);

        fabDel.setTranslationY(translationYaxis);
        fabAdd.setTranslationY(translationYaxis);

        fabOpenClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuOpen[0]){
                    CloseMenu();
                }
                else{
                    OpenMenu();
                }
            }

            private void OpenMenu() {
                menuOpen[0] = !menuOpen[0];
                fabOpenClose.setImageResource(R.drawable.baseline_keyboard_arrow_down_24);
                fabDel.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
                fabAdd.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
            }

            private void CloseMenu() {
                menuOpen[0] = !menuOpen[0];
                fabOpenClose.setImageResource(R.drawable.baseline_keyboard_arrow_up_24);
                fabDel.animate().translationY(translationYaxis).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
                fabAdd.animate().translationY(translationYaxis).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewMedTrackFragment dialog = new NewMedTrackFragment();
                dialog.show(getChildFragmentManager(), "");
            }
        });

        fabDel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                ClearAllWarning dialog = new ClearAllWarning();
//                dialog.show(getChildFragmentManager(),"");
                medTrackList.clear();
                mAdapter.notifyDataSetChanged();
            }
        }
        );





        mSerializer = new JSONSerializer("NoteToSelf.json", requireContext());

        try {
            medTrackList = mSerializer.load();
        } catch (Exception e) {
            medTrackList = new ArrayList<MedTrack>();
            Log.e("Error loading notes: ", "", e);
        }

        recyclerView =
                view.findViewById(R.id.recyclerView);

        mAdapter = new MedTrackAdapter(this, medTrackList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(requireContext());

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());


// set the adapter

        recyclerView.setAdapter(mAdapter);



        return view;

    }


    public void createNewMed(MedTrack mt){
        // Temporary code
        // mTempNote = n;
        medTrackList.add(mt);

        mAdapter.notifyDataSetChanged();
    }

    public void onResume() {
        super.onResume();
        mPrefs = requireActivity().getSharedPreferences("Note to self", MODE_PRIVATE);
    }

    public void saveNotes(){
        try{
            mSerializer.save(medTrackList);

        }catch(Exception e){
            Log.e("Error Saving Notes","", e);
        }
    }

    @Override
    public void onPause(){
        super.onPause();

        saveNotes();

    }

    public void removeItems() {
        medTrackList.clear();
        mAdapter.notifyDataSetChanged();
    }

    public void showMed(int noteToShow){

        ShowMedFragment dialog = new ShowMedFragment();

        dialog.sendNoteSelected(medTrackList.get(noteToShow));

        dialog.show(getChildFragmentManager(), "");

    }




    }

