package com.example.daan.bucketlist.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.daan.bucketlist.models.ListItem;
import com.example.daan.bucketlist.services.repositories.ListItemRepository;

import java.util.List;

public class ListItemViewModel extends AndroidViewModel {
    private ListItemRepository repository;
    private LiveData<List<ListItem>> allListItems;

    public ListItemViewModel(Application application) {
        super(application);
        this.repository = new ListItemRepository(application);
        this.allListItems = repository.getAllListItems();
    }

    public void insert(ListItem listItem) {
        repository.insert(listItem);
    }

    public void update(ListItem listItem) {
        repository.update(listItem);
    }

    public void delete(ListItem listItem) {
        repository.delete(listItem);
    }

    public void deleteAllListitems() {
        repository.deleteAllListItems();
    }

    public LiveData<List<ListItem>> getAllListItems() {
        return this.allListItems;
    }
}
