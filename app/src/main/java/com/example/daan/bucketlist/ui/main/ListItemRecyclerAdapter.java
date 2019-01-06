package com.example.daan.bucketlist.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.daan.bucketlist.MainActivity;
import com.example.daan.bucketlist.R;
import com.example.daan.bucketlist.models.ListItem;

import java.util.ArrayList;
import java.util.List;

public class ListItemRecyclerAdapter extends RecyclerView.Adapter<ListItemRecyclerAdapter.ListItemHolder> {
    private List<ListItem> listItems = new ArrayList<>();
    private ListItemViewModel mViewmodel;

    public ListItemRecyclerAdapter(ListItemViewModel mViewmodel) {
        this.mViewmodel = mViewmodel;
    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View viewItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ListItemHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListItemHolder holder, int position) {
        final ListItem currentListItem = listItems.get(position);
        holder.taskTitle.setText(currentListItem.getTaskTitle());
        holder.taskDescription.setText(currentListItem.getTaskDescription());
        holder.checkBox.setChecked(currentListItem.isCompleted());

        //Add onclick for Checkbox
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchCheckboxStatus(currentListItem);
                notifyDataSetChanged();
            }
        });
    }

    private void switchCheckboxStatus(ListItem listItem) {
        if (listItem.isCompleted()) {
            listItem.setCompleted(false);
            mViewmodel.update(listItem);
        } else {
            listItem.setCompleted(true);
            mViewmodel.update(listItem);
        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public void setNotes(List<ListItem> listItems) {
        this.listItems = listItems;
        notifyDataSetChanged();
    }

    public class ListItemHolder extends RecyclerView.ViewHolder {
        private TextView taskTitle;
        private TextView taskDescription;
        private CheckBox checkBox;

        public ListItemHolder(@NonNull View itemView) {
            super(itemView);
            taskTitle = itemView.findViewById(R.id.title_task);
            taskDescription = itemView.findViewById(R.id.description_task);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
