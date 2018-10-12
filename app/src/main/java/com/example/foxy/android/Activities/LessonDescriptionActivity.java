package com.example.foxy.android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foxy.android.R;
import com.example.foxy.android.models.Lesson;

import java.util.List;

public class LessonDescriptionActivity extends AppCompatActivity {

    private ImageView imageLesson;
    private TextView title;
    private TextView description;
    private List<Lesson> lessonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_description);

        imageLesson = findViewById(R.id.lesson_description_icon);
        title = findViewById(R.id.lesson_description_title);
        description = findViewById(R.id.lesson_description_text);

        Intent intent = getIntent();
        String lessonTitle;
        }


}
