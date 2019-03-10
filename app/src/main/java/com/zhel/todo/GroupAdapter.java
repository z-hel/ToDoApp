package com.zhel.todo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class GroupAdapter extends PagerAdapter {
    ArrayList<ToDoGroup> groupItems;
    Context context;

    public GroupAdapter(Context context, ArrayList<ToDoGroup> groupItems) {
        this.context = context;
        this.groupItems = groupItems;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.group_item_todo, container, false);
        container.addView(view);
        return view;
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        TextView groupName = container.getRootView().findViewById(R.id.group_name);
//        if (position < 0) position = 0;
//        if (position >= getCount()) position = getCount() - 1;
        groupName.setText(groupItems.get(position).getGroupName());
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object){
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        return groupItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
