package com.example.foxy.android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import com.example.foxy.android.R;
import com.example.foxy.android.models.Lesson;
import com.example.foxy.android.utils.ToastUtils;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView recordDate;
    private TextView lessonSumCount;
    private TextView lessonCount;
    private TextView rank;
    private TextView lastLesson;
    private SeekBar lessonSeekBar;
    private Button saveLessonButton;
    private Button startButton;
    private Button notesButton;
    private MenuItem shareMenuItem;
    private int count;
    private int lessonRecordCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Lesson lesson = new Lesson(0, new Date());

        unitGUI(lesson);
        onStartButtonClick();
        onNotesButtonClick();
        onSeekBarClick();
        onSaveLessonButtonClick();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == shareMenuItem.getItemId()) {
            shareStatistics();
        }
        return super.onOptionsItemSelected(item);
    }

    private void unitGUI(Lesson lesson) {
        recordDate = findViewById(R.id.main_activity_record_data);
        recordDate.setText(lesson.getFormatDate());

        lessonCount = findViewById(R.id.main_activity_lesson_count);
        lessonSumCount = findViewById(R.id.main_activity_lesson_record);
        lessonSeekBar = findViewById(R.id.main_activity_seek_bar);
        saveLessonButton = findViewById(R.id.main_activity_save_button);
        startButton = findViewById(R.id.main_activity_start_button);
        notesButton = findViewById(R.id.main_activity_notes_button);
        rank = findViewById(R.id.main_activity_rank);
        lastLesson = findViewById(R.id.main_activity_last_lesson);
        shareMenuItem = findViewById(R.id.activity_main_share);
    }

    private void onStartButtonClick() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LessonListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onNotesButtonClick() {
        notesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onSeekBarClick() {
        lessonSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                lessonCount.setText(String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void onSaveLessonButtonClick() {
        saveLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lessonCountToInt = Integer.parseInt(lessonCount.getText().toString());

                if (lessonSeekBar.getMax() >= (count + lessonCountToInt)) {
                    count = count + lessonCountToInt;
                    lessonSumCount.setText(String.valueOf(count));
                    checkRank(count);
                    if (lessonCountToInt > lessonRecordCount) {
                        lessonRecordCount = lessonCountToInt;
                        ToastUtils.shortInfoToast(getString(R.string.new_record_text), getApplicationContext());
                    }
                } else {
                    ToastUtils.shortInfoToast(getString(R.string.excess_lesson), getApplicationContext());
                }
            }
        });
    }

    private void checkRank(int lessonCount) {
        if (lessonCount >= 50) {
            rank.setText(R.string.rank_learner);
        }
        if (lessonCount >= 100) {
            rank.setText(R.string.rank_docent);
        }
        if (lessonCount >= 150) {
            rank.setText(R.string.rank_master);
        }
        if (lessonCount >= 200) {
            rank.setText(R.string.rank_professor);
        }
    }


    private void shareStatistics(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_STREAM, lessonRecordCount);
        startActivity(Intent.createChooser(intent, "Поделиться"));

    }



}
