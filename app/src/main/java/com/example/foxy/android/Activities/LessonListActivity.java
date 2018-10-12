package com.example.foxy.android.activities;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.example.foxy.android.R;
import com.example.foxy.android.adapter.DataAdapter;
import com.example.foxy.android.models.Lesson;
import com.example.foxy.android.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LessonListActivity extends AppCompatActivity {

    private List<Lesson> lessonList;
    private RecyclerView lessonListRecyclerView;
    private DataAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        lessonList = new ArrayList<>();
        addToList();

        lessonListRecyclerView = findViewById(R.id.lesson_list_recycler_view);
        adapter = new DataAdapter(this, lessonList);

        adapter.setClickListener(new DataAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.shortInfoToast(String.valueOf(position), getApplicationContext());


//                доработать, чтобы при нажатии открывался урок
//                Intent intent = new Intent(LessonListActivity.this, LessonDescriptionActivity.class);
//                intent.putExtra("position", position);
//
//                startActivity(intent);
            }
        });

        lessonListRecyclerView.setAdapter(adapter);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(LessonListActivity.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void addLesson(int imageLesson, String titleLesson, String descriptionLesson) {
        lessonList.add(new Lesson(imageLesson, titleLesson, descriptionLesson));
    }

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    private void addToList() {
        addLesson(R.drawable.lesson_list_icon_for_lesson_1, "Урок 1. Введение", "Description...");
        addLesson(R.drawable.lesson_list_icon_for_lesson_2, "Урок 2. Android Studio", "Description...");
        addLesson(R.drawable.lesson_list_icon_for_lesson_3, "Урок 3. Структура Android-проекта", "Description...");
        addLesson(R.drawable.lesson_list_icon_for_lesson_4, "Урок 4. Компоненты экрана. Виды Layouts", "Description...");
        addLesson(R.drawable.lesson_list_icon_for_lesson_5, "Урок 5. Параметры для View-элементов", "Description...");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке", "Description...");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке", "Description...");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке", "Description...");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке", "Description...");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке", "Description...");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке", "Description...");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке", "Description...");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке", "Description...");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке", "Description...");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке", "Description...");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке", "Description...");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке", "Description...");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке", "Description...");
    }
}
