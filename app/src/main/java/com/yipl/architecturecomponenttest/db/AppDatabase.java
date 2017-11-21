package com.yipl.architecturecomponenttest.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.yipl.architecturecomponenttest.db.convertors.DateConverters;
import com.yipl.architecturecomponenttest.db.dao.ProjectDao;
import com.yipl.architecturecomponenttest.db.entity.Project;

/**
 * Created by umesh on 11/15/17.
 */

@Database(entities = {Project.class}, version = 1)
@TypeConverters({DateConverters.class})
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "app-database";

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                    // don't do this. always do database queries in background thread. my preference rxjava
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract ProjectDao getProjectDao();
}
