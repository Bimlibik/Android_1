package com.example.foxy.android.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.foxy.android.R;
import com.example.foxy.android.fragments.LessonListFragment;
import com.example.foxy.android.fragments.NotesListFragment;
import com.example.foxy.android.fragments.StartMenuFragment;
import com.example.foxy.android.fragments.StatisticsFragment;
import com.example.foxy.android.utils.OnSelectedButtonListener;

public class MainActivity extends AppCompatActivity implements OnSelectedButtonListener {

    private StartMenuFragment startMenuFragment;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startMenuFragment = new StartMenuFragment();

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, startMenuFragment).commit();
    }

    @Override
    public void onButtonSelected(int buttonId) {
        switch (buttonId) {
            case R.id.fragment_start_menu_button_info:
                startMenuFragment.infoFragmentAction();
                break;
            case R.id.fragment_start_menu_image_view_info:
                startMenuFragment.infoFragmentAction();
                break;

            case R.id.fragment_start_menu_button_theory:
                startMenuFragment.otherFragmentAction(new LessonListFragment());
                break;
            case R.id.fragment_start_menu_image_view_theory:
                startMenuFragment.otherFragmentAction(new LessonListFragment());
                break;

            case R.id.fragment_start_menu_button_cheat:
                break;

            case R.id.fragment_start_menu_button_notes:
                startMenuFragment.otherFragmentAction(new NotesListFragment());
                break;
            case R.id.fragment_start_menu_image_view_notes:
                startMenuFragment.otherFragmentAction(new NotesListFragment());
                break;

            case R.id.fragment_start_menu_button_statistics:
                startMenuFragment.otherFragmentAction(new StatisticsFragment());
                break;
            case R.id.fragment_start_menu_image_view_statistics:
                startMenuFragment.otherFragmentAction(new StatisticsFragment());
                break;

            case R.id.fragment_start_menu_button_exit:
                startMenuFragment.closeApp();
                break;
            case R.id.fragment_start_menu_image_view_exit:
                startMenuFragment.closeApp();
                break;
        }
    }

//    @Override
//    public void onBackPressed() {
//        startMenuFragment.closeApp();
//    }


}
