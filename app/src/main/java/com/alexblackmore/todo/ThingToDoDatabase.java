package com.alexblackmore.todo;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = ThingToDo.class, version = 1, exportSchema = false)
public abstract class ThingToDoDatabase extends RoomDatabase {

    private static ThingToDoDatabase INSTANCE;

    public abstract ThingToDoDao thingToDoDao();

    public static ThingToDoDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ThingToDoDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ThingToDoDatabase.class, "thing_to_do_database")

                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ThingToDoDao mDao;

        PopulateDbAsync(ThingToDoDatabase db) {
            mDao = db.thingToDoDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            mDao.insert(new ThingToDo("Title 1", "Description 1", "High"));
            mDao.insert(new ThingToDo("Title 2", "Description 2", "Medium"));
            mDao.insert(new ThingToDo("Title 3", "Description 3", "Low"));
            return null;
        }
    }
}
