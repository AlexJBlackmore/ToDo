package com.alexblackmore.todo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ThingToDoRepository {
    private ThingToDoDao mDao;
    private LiveData<List<ThingToDo>> toDoList;

    ThingToDoRepository(Application application) {
        ThingToDoDatabase db = ThingToDoDatabase.getDatabase(application);
        mDao = db.thingToDoDao();
        toDoList = mDao.getAll();
    }

    // *******Get all********
    LiveData<List<ThingToDo>> getAll() {
        return toDoList;
    }



    // *******Update********
    private static class updateAsyncTask extends AsyncTask<ThingToDo, Void, Void> {
        private ThingToDoDao mAsyncTaskDao;

        updateAsyncTask(ThingToDoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ThingToDo... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    public void update(ThingToDo thingToDo){

    }




    // *******Insert********
    private static class insertAsyncTask extends AsyncTask<ThingToDo, Void, Void> {
        private ThingToDoDao mAsyncTaskDao;

        insertAsyncTask(ThingToDoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ThingToDo... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void insert (ThingToDo thingToDo) {
        new insertAsyncTask(mDao).execute(thingToDo);
    }






    // *******Delete all********
    private static class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private ThingToDoDao mAsyncTaskDao;

        deleteAllWordsAsyncTask(ThingToDoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    public void deleteAll()  {
        new deleteAllWordsAsyncTask(mDao).execute();
    }






    // *******Delete one********
    private static class deleteThingAsyncTask extends AsyncTask<ThingToDo, Void, Void> {
        private ThingToDoDao mAsyncTaskDao;

        deleteThingAsyncTask(ThingToDoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ThingToDo... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    public void deleteThingToDo(ThingToDo thingToDo)  {
        new deleteThingAsyncTask(mDao).execute(thingToDo);
    }
}
