package com.example.foxy.android.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foxy.android.R;
import com.example.foxy.android.models.Lesson;
import com.example.foxy.android.models.Notes;

import java.util.List;

public class NotesAdapter extends  RecyclerView.Adapter<NotesViewHolder> {

    private List<Notes> notesList = NotesList.getInstance().getNotesList();


    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_item, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, final int position) {
        holder.bindView(notesList.get(position), position);

        holder.removeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotesList.getInstance().removeFromList(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesList != null ? notesList.size() : 0;
    }

}
