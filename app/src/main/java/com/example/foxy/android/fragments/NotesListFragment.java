package com.example.foxy.android.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.foxy.android.R;
import com.example.foxy.android.list.NotesAdapter;
import com.example.foxy.android.list.NotesList;

public class NotesListFragment extends Fragment {

    private RecyclerView notesRecyclerView;
    private FloatingActionButton fabButton;
    private NotesAdapter notesAdapter;
    private ImageView removeImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_list, container, false);
        fabButton = view.findViewById(R.id.fragment_notes_fab);
        onFabButtonClick();
        notesRecyclerView = view.findViewById(R.id.fragment_notes_recycler_view);
        notesAdapter = new NotesAdapter();
        notesRecyclerView.setAdapter(notesAdapter);
        removeImageView = view.findViewById(R.id.notes_item_image_view_clear);
        return view;
    }

    private void onFabButtonClick() {
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotesList.getInstance().addToList(getString(R.string.note), getString(R.string.entry));
                notesAdapter.notifyDataSetChanged();
            }
        });
    }
}
