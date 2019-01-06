package com.example.daan.bucketlist.services.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.daan.bucketlist.models.ListItem;

import java.util.List;

@Dao
public interface ListItemDao {

    @Insert
    void insert(ListItem listItem);

    @Update
    void update(ListItem listItem);

    @Delete
    void delete(ListItem listItem);

    @Query("DELETE FROM listItem_table")
    void deleteAllListItems();

    @Query("SELECT * FROM listItem_table")
    LiveData<List<ListItem>> getAllListItems();
}
