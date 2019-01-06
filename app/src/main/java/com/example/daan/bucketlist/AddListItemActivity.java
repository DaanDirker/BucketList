package com.example.daan.bucketlist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.daan.bucketlist.models.ListItem;
import com.example.daan.bucketlist.ui.main.ListItemViewModel;

public class AddListItemActivity extends AppCompatActivity {

    private ListItemViewModel mViewModel;
    private ListItem newListItem;

    private EditText mTitleEdit;
    private EditText mDescriptionEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTitleEdit = findViewById(R.id.edit_title_edit);
        mDescriptionEdit = findViewById(R.id.edit_description_edit);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addListItem(view);
            }
        });
    }

    private void addListItem(View view) {
        String taskTitle = mTitleEdit.getText().toString();
        String taskDescription = mDescriptionEdit.getText().toString();

        if (!taskTitle.isEmpty()) {
            MainActivity.mViewModel.insert(new ListItem(taskTitle, taskDescription, false));
            finish();
        } else {
            Snackbar.make(view, "A task title is required", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
