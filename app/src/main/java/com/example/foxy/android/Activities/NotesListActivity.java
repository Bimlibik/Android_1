package com.example.foxy.android.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.example.foxy.android.R;
import com.example.foxy.android.list.NotesAdapter;
import com.example.foxy.android.list.NotesList;

public class NotesListActivity extends AppCompatActivity {

    private RecyclerView notesRecyclerView;
    private FloatingActionButton fabButton;
    private NotesAdapter notesAdapter;
    private ImageView removeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        fabButton = findViewById(R.id.notes_fab);
        onFabButtonClick();
        notesRecyclerView = findViewById(R.id.notes_recycler_view);
        notesAdapter = new NotesAdapter();
        notesRecyclerView.setAdapter(notesAdapter);
        removeImageView = findViewById(R.id.notes_item_image_view);
    }

    private void onFabButtonClick() {
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotesList.getInstance().addToList("Заметка", "Запись");
                notesAdapter.notifyDataSetChanged();
            }
        });
    }
}
