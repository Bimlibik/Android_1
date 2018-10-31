package com.example.foxy.android.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import com.example.foxy.android.utils.Constants;
import com.example.foxy.android.utils.ToastUtils;

import java.util.Date;

public class StatisticsActivity extends AppCompatActivity {

    private TextView recordDate;
    private TextView lessonSumCount; //количество пройденных уроков
    private TextView lessonCount; //пройдено уроков за день
    private TextView rank;
    private TextView lastLesson;
    private SeekBar lessonSeekBar;
    private Button saveLessonButton;
    private Button resetLessonButton;
    private MenuItem shareItem;
    private MenuItem settingsItem;
    private MenuItem quitItem;
    private SharedPreferences preferences;
    private int count;
    private int lessonRecordCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Lesson lesson = new Lesson(0, new Date());

        unitGUI(lesson);
        onSeekBarClick();
        onSaveLessonButtonClick();
        resetStatistics();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadStatistics();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveStatistics();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.SAVE_LESSON_SUM_COUNT, count);   //сохраняем количество пройденных уроков
        outState.putInt(Constants.SAVE_LESSON_RECORD_COUNT, lessonRecordCount);  // сохраняем счетчик рекорда
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt(Constants.SAVE_LESSON_SUM_COUNT);
        lessonRecordCount = savedInstanceState.getInt(Constants.SAVE_LESSON_RECORD_COUNT);
        lessonSumCount.setText(String.valueOf(count));
    }


    private void unitGUI(Lesson lesson) {
        recordDate = findViewById(R.id.statistics_activity_record_data);
        recordDate.setText(lesson.getFormatDate());

        lessonCount = findViewById(R.id.statistics_activity_lesson_count);
        lessonSumCount = findViewById(R.id.statistics_activity_lesson_record);
        lessonSeekBar = findViewById(R.id.statistics_activity_seek_bar);
        saveLessonButton = findViewById(R.id.statistics_activity_save_button);
        resetLessonButton = findViewById(R.id.statistics_activity_reset_button);
        rank = findViewById(R.id.statistics_activity_rank);
        lastLesson = findViewById(R.id.statistics_activity_last_lesson);
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
                        ToastUtils.shortInfoToast(getString(R.string.new_record_text) + " " + lessonRecordCount, getApplicationContext());
                    }
                } else {
                    ToastUtils.shortInfoToast(getString(R.string.excess_lesson), getApplicationContext());
                }
            }
        });
    }



    private void checkRank(int lessonCount) {
        if (lessonCount >= Constants.RANK_LEARNER) {
            rank.setText(R.string.rank_learner);
        }
        if (lessonCount >= Constants.RANK_DOCENT) {
            rank.setText(R.string.rank_docent);
        }
        if (lessonCount >= Constants.RANK_MASTER) {
            rank.setText(R.string.rank_master);
        }
        if (lessonCount >= Constants.RANK_PROFESSOR) {
            rank.setText(R.string.rank_professor);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_start_activity, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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

    private void shareInfo() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, R.string.share_lessons_learned + String.valueOf(lessonRecordCount));
        startActivity(intent);
    }

    private void closeApp() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.close_app);
        alertDialogBuilder.setMessage(null).setCancelable(false).
                setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void saveStatistics() {
        preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor rankEditor = preferences.edit();
        SharedPreferences.Editor lessonEditor = preferences.edit();
        SharedPreferences.Editor recordEditor = preferences.edit();

        rankEditor.putString(Constants.SAVE_STATISTICS_RANK, rank.getText().toString());
        lessonEditor.putInt(Constants.SAVE_STATISTICS_LESSONS, count);
        recordEditor.putInt(Constants.SAVE_STATISTICS_RECORD, lessonRecordCount);

        rankEditor.apply();
        lessonEditor.apply();
        recordEditor.apply();
    }

    private void loadStatistics() {
        preferences = getPreferences(MODE_PRIVATE);
        String savedRank = preferences.getString(Constants.SAVE_STATISTICS_RANK, "");
        rank.setText(savedRank);

        count = preferences.getInt(Constants.SAVE_STATISTICS_LESSONS, 0);
        lessonSumCount.setText(String.valueOf(count));

        lessonRecordCount = preferences.getInt(Constants.SAVE_STATISTICS_RECORD,0);
    }

    private void resetStatistics() {
        resetLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rank.setText(R.string.rank_newbie);
                lessonSumCount.setText(R.string.zero);
                count = 0;
                lessonRecordCount = 0;
            }
        });
    }


}




