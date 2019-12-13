package com.example.minefield.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {TopListRecord.class}, version = 2, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDataBase extends RoomDatabase {
    public abstract TopListDao topListDao();
    private static volatile AppDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, "app_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

