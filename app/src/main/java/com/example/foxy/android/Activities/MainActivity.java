package com.example.foxy.android.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foxy.android.Model.Lesson;
import com.example.foxy.android.R;


import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView reoordDate;
    private TextView lessonSumCount;
    private TextView lessonCount;
    private TextView rank;
    private TextView lastLesson;
    private SeekBar lessonSeekBar;
    private Button saveLessonButton;
    private Button startButton;
    private Button notesButton;
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


        // смена цвета текста при нажатии на кнопку
//        changeColorButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                welcomeTextView.setTextColor(getRandomColor());
//            }
//        });


        // переход на следующий экран

    }



    private void unitGUI(Lesson lesson) {
        reoordDate = findViewById(R.id.main_activity_record_data);
        reoordDate.setText(lesson.getFormatDate());

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
                int lessonCountToInt = Integer.parseInt(lessonCount.getText().toString());

                if (lessonSeekBar.getMax() >= (count + lessonCountToInt)) {
                    count = count + lessonCountToInt;
                    lessonSumCount.setText(String.valueOf(count));
                    checkRank(count);
                    if (lessonCountToInt > lessonRecordCount) {
                        lessonRecordCount = lessonCountToInt;
                        recordToast();
                    }
                } else {
                    excessToast();
                }
            }
        });
    }

    private void recordToast() {
        Toast toast = Toast.makeText(getApplicationContext(),
                R.string.new_record_text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void excessToast() {
        Toast toast = Toast.makeText(getApplicationContext(),
                R.string.excess_lesson, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
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

    // генерирует рандомный цвет
//    public int getRandomColor(){
//        Random rnd = new Random();
//        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256),
//                rnd.nextInt(256));
//    }

}
