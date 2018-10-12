package com.example.foxy.android.activities;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.os.Bundle;
import android.util.Log;
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
    private TextView lessonSumCount; //количество пройденных уроков
    private TextView lessonCount; //пройдено уроков за день
    private TextView rank;
    private TextView lastLesson;
    private SeekBar lessonSeekBar;
    private Button saveLessonButton;
    private Button startButton;
    private Button notesButton;
    private ShareActionProvider shareActionProvider;
    private MenuItem shareItem;
    private int count;
    private int lessonRecordCount;
    private final String TAG = "MAIN_ACTIVITY_TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "Запуск метода onCreated");

        Lesson lesson = new Lesson(0, new Date());

        unitGUI(lesson);
        onStartButtonClick();
        onNotesButtonClick();
        onSeekBarClick();
        onSaveLessonButtonClick();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Запуск метода onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Запуск метода onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Запуск метода onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Запуск метода onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Запуск метода onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Запуск метода onDestroy");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("lessonSumCount", count);   //сохраняем количество пройденных уроков
        outState.putInt("lessonRecordCount", lessonRecordCount);  // сохраняем счетчик рекорда

        Log.d(TAG, "Запуск метода onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt("lessonSumCount");
        lessonRecordCount = savedInstanceState.getInt("lessonRecordCount");
        lessonSumCount.setText(String.valueOf(count));

        Log.d(TAG, "Запуск метода onRestoreInstanceState");
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
                //достаем int из textView, которая показывает значение seekBar'a
                int lessonCountToInt = Integer.parseInt(lessonCount.getText().toString());

                if (lessonSeekBar.getMax() >= (count + lessonCountToInt)) {
                    count = count + lessonCountToInt;

                    //записываем рекорд в статистику, проверяем ранг
                    lessonSumCount.setText(String.valueOf(count));
                    checkRank(count);

                    // сравниваем текущее значение seekBar'a с предыдущим
                    if (lessonCountToInt > lessonRecordCount) {
                        lessonRecordCount = lessonCountToInt;
                        setShareIntent();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        shareItem = menu.findItem(R.id.activity_main_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        setShareIntent();
        return true;
    }


    private void setShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, String.valueOf(lessonRecordCount));

        //для обновления
        if (shareActionProvider != null) {
            shareActionProvider.setShareIntent(shareIntent);
        }
    }


}




