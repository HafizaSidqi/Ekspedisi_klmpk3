package com.example.Ekspedisi.entity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Rating.class} , version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    //akses menggunakan method abstract
    public abstract RatingDAO ratingDAO();

    private static AppDatabase appDatabase;
    public static AppDatabase iniDb(Context context){
        if(appDatabase == null)
            appDatabase = Room.databaseBuilder(context, AppDatabase.class,
                    "dbRating").allowMainThreadQueries().build();

        return appDatabase;
    }
}