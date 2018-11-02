package com.example.foxy.android.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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
    private DrawerLayout drawerLayout;
    private Toolbar toolBar;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unitGUI();
        onNavigationClick();

        drawerToggle = setupDrawerToggle();
        drawerLayout.addDrawerListener(drawerToggle);

        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        startMenuFragment = new StartMenuFragment();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, startMenuFragment).commit();
    }

    private void unitGUI() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolBar = findViewById(R.id.toolbar);
    }

    private void onNavigationClick() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.open, R.string.close);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
