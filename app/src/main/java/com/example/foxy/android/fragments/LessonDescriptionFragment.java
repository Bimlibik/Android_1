package com.example.foxy.android.fragments;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.example.foxy.android.R;
import com.example.foxy.android.utils.Constants;


public class LessonDescriptionFragment extends Fragment {

    private WebView descriptionWebView;
    private Button buttonNext;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson_description, container, false);
        descriptionWebView = view.findViewById(R.id.fragment_lesson_description_web_view);
        buttonNext = view.findViewById(R.id.fragment_lesson_description_button_next);
        openFile();
        return view;
    }

    public void openFile() {
        Bundle bundle = getArguments();
        String title;
        if (bundle != null) {
            int id = bundle.getInt(Constants.KEY_INFO_FILE) + 1;
            String infoFile = "file:///android_asset/lessons/lesson_" + id + "/lesson" + id + ".html";
            descriptionWebView.loadUrl(infoFile);
            if (id == 0) {
                title = getString(R.string.about_app);
                getActivity().setTitle(title);
            } else {
                title = getString(R.string.lesson) + " " + id;
                getActivity().setTitle(title);
            }
        }
    }

}
