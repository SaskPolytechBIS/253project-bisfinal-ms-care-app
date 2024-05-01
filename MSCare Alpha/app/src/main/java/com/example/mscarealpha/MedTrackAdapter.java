package com.example.mscarealpha;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mscarealpha.ui.medtrack.MedTrackFragment;

import java.util.List;

public class MedTrackAdapter extends RecyclerView.Adapter<MedTrackAdapter.ListItemHolder> {

    private List<MedTrack> mMedList;

    private MedTrackFragment mMedTrackFragment;

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        return new ListItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        MedTrack medTrack = mMedList.get(position);

        holder.mMed.setText(medTrack.getMed());

        holder.mDos.setText(medTrack.getDos());

        // Show the first 15 characters of the actual note

        // Unless a short note then show half
    }

    @Override
    public int getItemCount() {
        return mMedList.size();
    }

    public class ListItemHolder extends RecyclerView.ViewHolder {
        TextView mMed;
        TextView mDos;

        public ListItemHolder(View view) {

            super(view);

            mMed =view.findViewById(R.id.txtViewMed);

            mDos=view.findViewById(R.id.txtViewDosage);

//            view.setClickable(true);
//
//            view.setOnClickListener(this);
        }

//        @Override
//
//        public void onClick(View view) {
//            mMedTrackFragment.showNote(getAdapterPosition());
//        }
    }
    public MedTrackAdapter(MedTrackFragment medTrackFragment,

                       List<MedTrack> medTrackList) {



        mMedTrackFragment = medTrackFragment;

        mMedList = medTrackList;

    }
}
