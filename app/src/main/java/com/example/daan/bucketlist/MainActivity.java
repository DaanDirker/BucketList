package com.example.daan.bucketlist;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.daan.bucketlist.models.ListItem;
import com.example.daan.bucketlist.ui.main.ListItemRecyclerAdapter;
import com.example.daan.bucketlist.ui.main.ListItemViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static ListItemViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Init Viewmodel
        mViewModel = ViewModelProviders.of(this).get(ListItemViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.bucket_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ListItemRecyclerAdapter mAdapter = new ListItemRecyclerAdapter(mViewModel);
        recyclerView.setAdapter(mAdapter);

        mViewModel.getAllListItems().observe(this, new Observer<List<ListItem>>() {
            @Override
            public void onChanged(@Nullable List<ListItem> listItems) {
                mAdapter.setNotes(listItems);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddListItemActivity.class);
                startActivity(intent);
            }
        });
    }
}
