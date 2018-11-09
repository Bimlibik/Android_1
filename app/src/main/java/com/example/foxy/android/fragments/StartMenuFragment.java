package com.example.foxy.android.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.PreferenceFragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


import com.example.foxy.android.R;
import com.example.foxy.android.utils.Constants;
import com.example.foxy.android.utils.OnSelectedButtonListener;

public class StartMenuFragment extends Fragment {

    private Button infoButton;
    private Button theoryButton;
    private Button cheatButton;
    private Button exitButton;
    private Button notesButton;
    private Button statisticsButton;

    private ImageView infoImageView;
    private ImageView theoryImageView;
    private ImageView cheatImageView;
    private ImageView exitImageView;
    private ImageView notesImageView;
    private ImageView statisticsImageView;

    private MenuItem shareMenuItem;

    private OnSelectedButtonListener buttonListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_menu, container, false);

        unitGUI(view);
        onButtonClick();
        getActivity().setTitle(getString(R.string.app_name));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnSelectedButtonListener) {
            buttonListener = (OnSelectedButtonListener) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void unitGUI(View view) {
        infoButton = view.findViewById(R.id.fragment_start_menu_button_info);
        theoryButton = view.findViewById(R.id.fragment_start_menu_button_theory);
        cheatButton = view.findViewById(R.id.fragment_start_menu_button_cheat);
        notesButton = view.findViewById(R.id.fragment_start_menu_button_notes);
        statisticsButton = view.findViewById(R.id.fragment_start_menu_button_statistics);
        exitButton = view.findViewById(R.id.fragment_start_menu_button_exit);

        infoImageView = view.findViewById(R.id.fragment_start_menu_image_view_info);
        theoryImageView = view.findViewById(R.id.fragment_start_menu_image_view_theory);
        cheatImageView = view.findViewById(R.id.fragment_start_menu_image_view_cheat);
        notesImageView = view.findViewById(R.id.fragment_start_menu_image_view_notes);
        statisticsImageView = view.findViewById(R.id.fragment_start_menu_image_view_statistics);
        exitImageView = view.findViewById(R.id.fragment_start_menu_image_view_exit);

        shareMenuItem = view.findViewById(R.id.menu_start_fragment_share);
    }

    public void onButtonClick() {
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener.onButtonSelected(v.getId());
            }
        });
        infoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener.onButtonSelected(v.getId());
            }
        });

        theoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener.onButtonSelected(v.getId());
            }
        });
        theoryImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        notesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener.onButtonSelected(v.getId());
            }
        });
        notesImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener.onButtonSelected(v.getId());
            }
        });

        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener.onButtonSelected(v.getId());
            }
        });
        statisticsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener.onButtonSelected(v.getId());
            }
        });
        exitImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener.onButtonSelected(v.getId());
            }
        });
    }

    public void infoFragmentAction() {
        LessonDescriptionFragment info = new LessonDescriptionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_INFO_FILE, Constants.INFO_FILE_ID);
        info.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).
                replace(R.id.fragment_container, info).
                addToBackStack(null).
                commit();
    }

    public void otherFragmentAction(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).
                replace(R.id.fragment_container, fragment).
                addToBackStack(null).
                commit();
    }

    public void closeApp() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getActivity());
        alertDialogBuilder.setTitle(R.string.close_app);
        alertDialogBuilder.setMessage(null).setCancelable(false).
                setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getActivity().moveTaskToBack(true);
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


    public void shareInfo() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.i_studying_android));

        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_start_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_start_fragment_share:
                shareInfo();
                return true;
            case R.id.menu_start_fragment_settings:
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new SettingsFragment())
                        .addToBackStack(null)
                        .commit();
                return true;
            case R.id.menu_start_fragment_exit:
                closeApp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
