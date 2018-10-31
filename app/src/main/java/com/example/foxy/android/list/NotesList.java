package com.example.foxy.android.list;

import com.example.foxy.android.models.Notes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class NotesList {
    private static final NotesList ourInstance = new NotesList();
    private List<Notes> notesList;

    public static NotesList getInstance() {
        return ourInstance;
    }

    private NotesList() {
        notesList = new ArrayList<>();
//        for (int i = 0; i < Constants.NUMBER_OF_LESSONS; i++) {
//            Notes note = new Notes("Заметка № " + (i + 1));
//            note.setNote("Запись № " + (i+1));
//            note.setDateOfNote(new Date());
//            notesList.add(note);
//        }
    }

    public List<Notes> getNotesList() {
        return notesList;
    }

    public void addToList(String title, String text) {
        Random random = new Random();
        int count = random.nextInt(101);
        Notes note = new Notes(title + " " + count);
        note.setNote(text + " " + count);
        note.setDateOfNote(new Date());
        notesList.add(note);
    }

    public void removeFromList(int position) {
        notesList.remove(position);
    }
}
