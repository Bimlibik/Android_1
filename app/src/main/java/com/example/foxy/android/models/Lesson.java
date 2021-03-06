package com.example.foxy.android.models;

import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Lesson {
    private String titleLesson;
    private String descriptionLesson;
    private int imageLesson;
    private int recordLessonCount;
    private Date recordDate;


    public Lesson(int recordLessonCount, Date recordDate) {
        this.recordLessonCount = recordLessonCount;
        this.recordDate = recordDate;
    }

    public Lesson(int imageLesson, String titleLesson) {
        this.titleLesson = titleLesson;
        this.imageLesson = imageLesson;
    }

    public int getRecordLessonCount() {
        return recordLessonCount;
    }

    public void setRecordLessonCount(int recordLessonCount) {
        this.recordLessonCount = recordLessonCount;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public String getFormatDate() {
        return new SimpleDateFormat("dd MM yyyy", Locale.ROOT).format(recordDate);
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getTitleLesson() {
        return titleLesson;
    }

    public void setTitleLesson(String titleLesson) {
        this.titleLesson = titleLesson;
    }

    public String getDescriptionLesson() {
        return descriptionLesson;
    }

    public void setDescriptionLesson(String descriptionLesson) {
        this.descriptionLesson = descriptionLesson;
    }

    public int getImageLesson() {
        return imageLesson;
    }

    public void setImageLesson(int imageLesson) {
        this.imageLesson = imageLesson;
    }

}

