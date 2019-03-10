package com.zhel.todo;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ToDoAdapter extends ArrayAdapter<ToDoDataModel> {
    private Context context;
    private ArrayList<ToDoDataModel> toDoList;
    private OnClickListener onClickListener;
    private OnLongClickListener onLongClickListener;

    public ToDoAdapter(Context context, ArrayList<ToDoDataModel> toDoList, OnClickListener onClickListener, OnLongClickListener onLongClickListener) {
        super(context, R.layout.group_item_todo, toDoList);
        this.context = context;
        this.toDoList = toDoList;
        this.onClickListener = onClickListener;
        this.onLongClickListener = onLongClickListener;
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

        v.setOnClickListener(v1 -> {
            onClickListener.onClick(position);
        });
        v.setOnLongClickListener(v2 -> {
            onLongClickListener.onLongClick(position);
            return true;
        });

        return v;
    }

    public interface OnClickListener {
        void onClick(int position);
    }
    public interface OnLongClickListener {
        void onLongClick(int position);
    }

}
