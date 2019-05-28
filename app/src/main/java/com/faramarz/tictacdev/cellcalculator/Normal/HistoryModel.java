package com.faramarz.tictacdev.cellcalculator.Normal;

public class HistoryModel {

    private int id;
    private String description,title, date,doublingtime;

    public HistoryModel() {
    }


    public String getDoublingtime() {
        return doublingtime;
    }

    public void setDoublingtime(String doublingtime) {
        this.doublingtime = doublingtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
