package com.example.foxy.android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.foxy.android.R;
import com.example.foxy.android.utils.Constants;


public class LessonDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_description);

        WebView descriptionWebView = findViewById(R.id.activity_lesson_description_web_view);

        Intent intent = getIntent();
        int id = (intent.getIntExtra(Constants.POSITION, 0)) + 1;
        String lessonName = "file:///android_asset/lessons/lesson_" + id + "/lesson" + id + ".html";
        descriptionWebView.getSettings().setJavaScriptEnabled(true);
        descriptionWebView.loadUrl(lessonName);
    }
}
