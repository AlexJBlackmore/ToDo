package com.alexblackmore.todo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "things_to_do_table")
public class ThingToDo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;

    private String importance;

    public ThingToDo(String title, String description, String importance) {
        this.title = title;
        this.description = description;
        this.importance = importance;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getImportance() {
        return importance;
    }
}
