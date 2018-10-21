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

    public NotesViewHolder(View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.notes_item_title_text_view);
        noteTextView = itemView.findViewById(R.id.notes_item_note_text_view);
        dateTextView = itemView.findViewById(R.id.notes_item_date_text_view);
        removeImageView = itemView.findViewById(R.id.notes_item_image_view);
    }

    public void bindView(Notes note, final int index) {
        titleTextView.setText(note.getTitle());
        noteTextView.setText(note.getNote());
        dateTextView.setText(note.getFormatDateOfNote());

//        removeImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }


}

