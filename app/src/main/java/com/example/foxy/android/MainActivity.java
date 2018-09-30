package com.example.foxy.android;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView welcomeTextView;
    Button changeColorButton;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeColorButton = findViewById(R.id.change_color_button);
        welcomeTextView = findViewById(R.id.welcome_text_view);
        startButton = findViewById(R.id.start_button);

        // смена цвета текста при нажатии на кнопку
        changeColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcomeTextView.setTextColor(getRandomColor());
            }
        });

        // переход на следующий экран
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LessonList.class);
                startActivity(intent);
            }
        });
    }

    // генерирует рандомный цвет
    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256),
                rnd.nextInt(256));
    }

}
