package com.example.daan.bucketlist.services.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.example.daan.bucketlist.models.ListItem;
import com.example.daan.bucketlist.services.room.ListItemDao;
import com.example.daan.bucketlist.services.room.ListItemDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListItemRepository {
    private ListItemDao listItemDao;
    private LiveData<List<ListItem>> allListItems;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public ListItemRepository(Application application) {
        ListItemDatabase database = ListItemDatabase.getInstance(application);
        this.listItemDao = database.listItemDao();
        this.allListItems = listItemDao.getAllListItems();
    }

    public void insert(final ListItem listItem) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                listItemDao.insert(listItem);
            }
        });
    }

    public void update(final ListItem listItem) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                listItemDao.update(listItem);
            }
        });
    }

    public void delete(final ListItem listItem) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                listItemDao.delete(listItem);
            }
        });
    }

    public void deleteAllListItems() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                listItemDao.deleteAllListItems();
            }
        });
    }

    public LiveData<List<ListItem>> getAllListItems() {
        return listItemDao.getAllListItems();
    }
}
