package com.alexblackmore.todo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ThingToDoViewModel extends AndroidViewModel {

    private ThingToDoRepository mRepository;
    private LiveData<List<ThingToDo>> thingToDoList;

    public ThingToDoViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ThingToDoRepository(application);
        thingToDoList = mRepository.getAll();
    }

    LiveData<List<ThingToDo>> getAll() {
        return thingToDoList;
    }

    public void insert(ThingToDo thingToDo) {
        mRepository.insert(thingToDo);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public void deleteThingToDo(ThingToDo thingToDo) {
        mRepository.deleteThingToDo(thingToDo);
    }

    public void update(ThingToDo thingToDo) {
        mRepository.update(thingToDo);
    }
}
