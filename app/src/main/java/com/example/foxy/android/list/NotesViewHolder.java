package com.example.foxy.android.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foxy.android.R;
import com.example.foxy.android.models.Notes;


public class NotesViewHolder extends RecyclerView.ViewHolder {

    TextView titleTextView;
    TextView noteTextView;
    TextView dateTextView;
    ImageView removeImageView;

    public NotesViewHolder(final View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.notes_item_text_view_title);
        noteTextView = itemView.findViewById(R.id.notes_item_text_view_note);
        dateTextView = itemView.findViewById(R.id.notes_item_text_view_date);
        removeImageView = itemView.findViewById(R.id.notes_item_image_view_clear);
    }

    public void bindView(Notes note, final int index) {
        titleTextView.setText(note.getTitle());
        noteTextView.setText(note.getNote());
        dateTextView.setText(note.getFormatDateOfNote());
    }

}

