package com.example.foxy.android.fragments;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foxy.android.R;
import com.example.foxy.android.list.DataAdapter;
import com.example.foxy.android.models.Lesson;
import com.example.foxy.android.utils.Constants;
import com.example.foxy.android.utils.ToastUtils;
import java.util.ArrayList;
import java.util.List;

public class LessonListFragment extends Fragment {

    private List<Lesson> lessonList;
    private RecyclerView lessonListRecyclerView;
    private DataAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson_list, container, false);

        lessonList = new ArrayList<>();
        addToList();

        lessonListRecyclerView = view.findViewById(R.id.fragment_lesson_list_recycler_view);
        adapter = new DataAdapter(getContext(), lessonList);
        onListItemListener();
        lessonListRecyclerView.setAdapter(adapter);
        return view;
    }

    public void onListItemListener() {
        adapter.setClickListener(new DataAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.shortInfoToast(String.valueOf(position), getContext());
                onListItemAction(position);
            }
        });
    }

    public void onListItemAction(int position) {
        LessonDescriptionFragment lesson = new LessonDescriptionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_INFO_FILE, position);
        lesson.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).
                replace(R.id.fragment_container, lesson).
                addToBackStack(null).
                commit();
    }

    public void addLesson(int imageLesson, String titleLesson) {
        lessonList.add(new Lesson(imageLesson, titleLesson));
    }

    public void addToList() {
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
