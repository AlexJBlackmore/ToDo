package com.alexblackmore.todo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ThingToDoDao {

    @Insert
    void insert(ThingToDo thingToDo);

    @Update
    void update(ThingToDo thingToDo);

    @Delete
    void delete(ThingToDo thingToDo);

    @Query("DELETE FROM things_to_do_table")
    void deleteAll();

    @Query("SELECT * FROM things_to_do_table ORDER BY importance DESC")
    LiveData<List<ThingToDo>> getAll();
}
