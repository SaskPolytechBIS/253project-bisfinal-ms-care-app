package com.example.mscarealpha.ui.notes;


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

import com.example.mscarealpha.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AppointmentNotesFragment extends Fragment {

    private List<AppointmentNotes> noteList = new ArrayList<>();

    private NoteAdapter mAdapter;

    private JSONSerializerForNotes mSerializer;

    private SharedPreferences mPrefs;

    private RecyclerView recyclerView;

    public static AppointmentNotesFragment newInstance() {
        return new AppointmentNotesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        FloatingActionButton fabAdd_Notes = view.findViewById(R.id.fabAdd_Notes);
        FloatingActionButton fabDel_Notes = view.findViewById(R.id.fabDel_Notes);

        fabAdd_Notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogNewNote dialog = new DialogNewNote ();
                dialog.show(getChildFragmentManager(), "");
            }
        });

        fabDel_Notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteList.clear();
                mAdapter.notifyDataSetChanged();
            }
        });

        mSerializer = new JSONSerializerForNotes("AppointmentNotes.json", requireContext());

        try {
            noteList = mSerializer.load();
        } catch (Exception e) {
            noteList = new ArrayList<AppointmentNotes>();
            Log.e("Error loading notes: ", "", e);
        }
        recyclerView =
                view.findViewById(R.id.recyclerView);

        mAdapter = new NoteAdapter(this, noteList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(requireContext());

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());


// set the adapter

        recyclerView.setAdapter(mAdapter);
        return view;


    }
    public void createNewNote(AppointmentNotes an){
        // Temporary code
        //mTempNote = n;

        noteList.add(an);
        mAdapter.notifyDataSetChanged();
    }
    public void onResume(){
        super.onResume();
        mPrefs = requireActivity().getSharedPreferences("Appointment Notes", MODE_PRIVATE);
    }


    public void showNote(int noteToShow){
        DialogShowNote dialog = new DialogShowNote();
        dialog.sendNoteSelected(noteList.get(noteToShow));
        dialog.show(getChildFragmentManager(), "");
    }

    public void saveNotes(){
        try{
            mSerializer.save(noteList);

        }catch(Exception e){
            Log.e("Error Saving Notes","", e);
        }
    }


    @Override
    public void onPause(){
        super.onPause();

        saveNotes();

    }

    }
