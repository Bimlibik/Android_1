package com.example.foxy.android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import com.example.foxy.android.R;
import com.example.foxy.android.list.DataAdapter;
import com.example.foxy.android.models.Lesson;
import com.example.foxy.android.utils.Constants;
import com.example.foxy.android.utils.ToastUtils;
import java.util.ArrayList;
import java.util.List;

public class LessonListActivity extends AppCompatActivity {

    private List<Lesson> lessonList;
    private RecyclerView lessonListRecyclerView;
    private DataAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list);

        lessonList = new ArrayList<>();
        addToList();

        lessonListRecyclerView = findViewById(R.id.lesson_list_recycler_view);
        adapter = new DataAdapter(this, lessonList);
        onListItemClick();
        lessonListRecyclerView.setAdapter(adapter);


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(LessonListActivity.this, StatisticsActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }



    private void onListItemClick() {
        adapter.setClickListener(new DataAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.shortInfoToast(String.valueOf(position), getApplicationContext());
                Intent intent = new Intent(LessonListActivity.this, LessonDescriptionActivity.class);
                intent.putExtra(Constants.POSITION, position);
                startActivity(intent);
            }
        });
    }

    private void addLesson(int imageLesson, String titleLesson) {
        lessonList.add(new Lesson(imageLesson, titleLesson));
    }

    private void addToList() {
        addLesson(R.drawable.lesson_list_icon_for_lesson_1, "Урок 1. Android Studio");
        addLesson(R.drawable.lesson_list_icon_for_lesson_2, "Урок 2. Структура Android-проекта");
        addLesson(R.drawable.lesson_list_icon_for_lesson_3, "Урок 3. Компоненты экрана. Виды Layouts");
        addLesson(R.drawable.lesson_list_icon_for_lesson_4, "Урок 4. Параметры для View-элементов");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок 5. Меню. ShareActionProvider");
        addLesson(R.drawable.lesson_list_icon_for_lesson_6, "Урок 6. Хранение данных с помощью SharedPreferences");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке");
        addLesson(R.drawable.lesson_list_icon_for_lesson_bn, "Урок б/н. В разработке");
    }
}
