package com.zhel.todo;

import java.util.ArrayList;

public class ToDoGroup {
    private String groupName;
    private ArrayList<ToDoDataModel> items;

    public ToDoGroup(String groupName, ArrayList<ToDoDataModel> items) {
        this.groupName = groupName;
        this.items = items;
    }

    public String getGroupName() {
        return groupName;
    }

    public ArrayList<ToDoDataModel> getItems() {
        return items;
    }
}
