package com.zhel.todo;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ToDoAdapter extends ArrayAdapter<ToDoDataModel> {
    private Context context;
    private ArrayList<ToDoDataModel> toDoList;

    public ToDoAdapter(Context context, ArrayList<ToDoDataModel> toDoList) {
        super(context, R.layout.activity_main, toDoList);
        this.context = context;
        this.toDoList = toDoList;
    }

    @Nullable
    @Override
    public ToDoDataModel getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.item_todo_list, null);
        }
        ToDoDataModel p = getItem(position);
        TextView textItem = v.findViewById(R.id.text_item);

        textItem.setText(p.getTextItem());
        if (p.isStrikeout()) textItem.setPaintFlags(textItem.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        else textItem.setPaintFlags(Paint.ANTI_ALIAS_FLAG);

        return v;
    }

}
