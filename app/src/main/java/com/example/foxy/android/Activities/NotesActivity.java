package com.example.foxy.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.foxy.android.R;


public class NotesActivity extends AppCompatActivity {

    FloatingActionButton fabAddNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        onFabButtonClick();

    }

    // переход на главный экран
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(NotesActivity.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

//    private void onFabButtonClick() {
//        fabAddNotes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast toast = Toast.makeText(getApplicationContext(),
//                        com.example.foxy.android.R.string.toast_add_notes, Toast.LENGTH_SHORT);
//            }
//        });
//    }
}
