package com.example.foxy.android.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Notes {
    private String title;
    private String note;
    private Date dateOfNote;

    public Notes(String title, String note, Date dateOfNote) {
        this.title = title;
        this.note = note;
        this.dateOfNote = dateOfNote;
    }

    public Notes(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDateOfNote() {
        return dateOfNote;
    }

    public String getFormatDateOfNote() {
        return new SimpleDateFormat("dd MM yyyy", Locale.ROOT).format(dateOfNote);
    }

    public void setDateOfNote(Date dateOfNote) {
        this.dateOfNote = dateOfNote;
    }
}
