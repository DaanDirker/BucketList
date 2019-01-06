package com.example.daan.bucketlist.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "listItem_table")
public class ListItem implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String taskTitle;
    private String taskDescription;
    private boolean isCompleted;

    public ListItem(String taskTitle, String taskDescription, boolean isCompleted) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.isCompleted = isCompleted;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ListItem> CREATOR = new Creator<ListItem>() {
        @Override
        public ListItem createFromParcel(Parcel in) {
            return new ListItem(in);
        }

        @Override
        public ListItem[] newArray(int size) {
            return new ListItem[size];
        }
    };

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getId() {
        return id;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    protected ListItem(Parcel in) {
        id = in.readInt();
        taskTitle = in.readString();
        taskDescription = in.readString();
        isCompleted = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(taskTitle);
        dest.writeString(taskDescription);
        dest.writeByte((byte) (isCompleted ? 1 : 0));
    }
}
