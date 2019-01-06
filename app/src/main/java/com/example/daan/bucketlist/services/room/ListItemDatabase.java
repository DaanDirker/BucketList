package com.example.daan.bucketlist.services.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.daan.bucketlist.models.ListItem;

@Database(entities = {ListItem.class}, version = 1, exportSchema = false)
public abstract class ListItemDatabase extends RoomDatabase {

    private static ListItemDatabase instance;

    public abstract ListItemDao listItemDao();

    public static synchronized ListItemDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ListItemDatabase.class, "list_item_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
