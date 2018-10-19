package com.example.foxy.android.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.foxy.android.R;

public class StartActivity extends AppCompatActivity {

    private Button infoButton;
    private Button theoryButton;
    private Button cheatButton;
    private Button exitButton;
    private Button notesButton;
    private Button statisticsButton;

    private ImageView infoImageView;
    private ImageView theoryImageView;
    private ImageView cheatImageView;
    private ImageView exitImageView;
    private ImageView notesImageView;
    private ImageView statisticsImageView;

    private MenuItem shareMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        unitGUI();
        onInfoClick();
        onTheoryClick();
        onCheatClick();
        onNotesClick();
        onStatisticsClick();
        onExitClick();
    }

    private void unitGUI() {
        infoButton = findViewById(R.id.start_activity_button_info);
        theoryButton = findViewById(R.id.start_activity_button_theory);
        cheatButton = findViewById(R.id.start_activity_button_cheat);
        notesButton = findViewById(R.id.start_activity_button_notes);
        statisticsButton = findViewById(R.id.start_activity_button_statistics);
        exitButton = findViewById(R.id.start_activity_button_exit);

        infoImageView = findViewById(R.id.start_activity_image_view_info);
        theoryImageView = findViewById(R.id.start_activity_image_view_theory);
        cheatImageView = findViewById(R.id.start_activity_image_view_cheat);
        notesImageView = findViewById(R.id.start_activity_image_view_notes);
        statisticsImageView = findViewById(R.id.start_activity_image_view_statistics);
        exitImageView = findViewById(R.id.start_activity_image_view_exit);

        shareMenuItem = findViewById(R.id.menu_start_activity_share);
    }

    private void onInfoClick() {
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = -1;
                Intent intent = new Intent(StartActivity.this, LessonDescriptionActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        infoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void onTheoryClick() {
        theoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, LessonListActivity.class);
                startActivity(intent);
            }
        });

        theoryImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, LessonListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onCheatClick() {
        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cheatImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void onNotesClick() {
        notesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });

        notesImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onStatisticsClick() {
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, StatisticsActivity.class);
                startActivity(intent);
            }
        });

        statisticsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, StatisticsActivity.class);
                startActivity(intent);
            }
        });
    }


    private void onExitClick() {
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeApp();
            }
        });

        exitImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeApp();
            }
        });
    }

    @Override
    public void onBackPressed() {
        closeApp();
    }

    private void closeApp() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Закрыть приложение?");
        alertDialogBuilder.setMessage(null).setCancelable(false).
                setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                }).setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void shareInfo() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.i_studying_android));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_start_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_start_activity_share:
                shareInfo();
                return true;
            case R.id.menu_start_activity_settings:
                return true;
            case R.id.menu_start_activity_exit:
                closeApp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
