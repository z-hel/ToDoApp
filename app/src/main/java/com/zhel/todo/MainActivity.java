package com.zhel.todo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ToDoAdapter itemsAdapter;
    private ArrayList<ToDoDataModel> items;
    private ListView lvItems;

    private ArrayList<ToDoGroup> groupItems;
    private ViewPager groupPager;
    private GroupAdapter groupAdapter;
    private Button deleteGroupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        deleteGroupButton = findViewById(R.id.delete_group);
        items = new ArrayList<>();

        groupItems = new ArrayList<>();
        groupPager = findViewById(R.id.group_todo_pager);
        groupAdapter = new GroupAdapter(this, groupItems);

        groupPager.setAdapter(groupAdapter);

        if (groupItems.isEmpty()) {
            deleteGroupButton.setVisibility(View.INVISIBLE);
        } else deleteGroupButton.setVisibility(View.VISIBLE);

    }


    //TODO
    private void strikeoutListViewItemListener(int pos) {
        items = groupItems.get(groupPager.getCurrentItem()).getItems();
                    if (items.get(pos).isStrikeout())
                        items.get(pos).setStrikeout(false);
                    else items.get(pos).setStrikeout(true);

                    itemsAdapter.notifyDataSetChanged();
    }

    private void deleteItem(int pos) {
        items = groupItems.get(groupPager.getCurrentItem()).getItems();
        items.remove(pos);
        itemsAdapter.notifyDataSetChanged();

    }
    //TODO

    public void onAddGroup(View v) {
        EditText etNewGroup = findViewById(R.id.et_new_group);
        String textItem = etNewGroup.getText().toString();
//        items = new ArrayList<>();
        groupItems.add(new ToDoGroup(textItem, new ArrayList<ToDoDataModel>()));
        groupAdapter.notifyDataSetChanged();

        etNewGroup.setText("");

        lvItems = groupPager.getChildAt(groupPager.getCurrentItem()).findViewById(R.id.lv_items);
        items = groupItems.get(groupPager.getCurrentItem()).getItems();
        itemsAdapter = new ToDoAdapter(groupPager.getContext(), items, this::deleteItem, this::strikeoutListViewItemListener);
        lvItems.setAdapter(itemsAdapter);

        if (groupItems.isEmpty()) {
            deleteGroupButton.setVisibility(View.INVISIBLE);
        } else deleteGroupButton.setVisibility(View.VISIBLE);


    }

    public void onDeleteGroup(View v) {
        int currentItem = groupPager.getCurrentItem();
        if (groupItems.get(currentItem).getItems().isEmpty()) {

            if (currentItem > 0) {
                groupPager.setCurrentItem(currentItem - 1);
//                groupPager.removeViewAt(currentItem);
//                groupAdapter.notifyDataSetChanged();
//                groupItems.remove(currentItem);
//                groupAdapter.notifyDataSetChanged();
                items = groupItems.get(currentItem-1).getItems();
                groupItems.remove(currentItem);
                groupAdapter.notifyDataSetChanged();
                itemsAdapter = new ToDoAdapter(groupPager.getContext(), items, this::deleteItem, this::strikeoutListViewItemListener);
                itemsAdapter.notifyDataSetChanged();

            }
            //TODO FUCKING DELETE
            if (currentItem == 0 && groupItems.size() > 1) {
//                groupPager.removeViewAt(0);
//                groupAdapter.notifyDataSetChanged();
//                items = groupItems.get(currentItem+1).getItems();
                items = groupItems.get(currentItem+1).getItems();
                groupItems.remove(currentItem);
                itemsAdapter = new ToDoAdapter(groupPager.getContext(), items, this::deleteItem, this::strikeoutListViewItemListener);
                itemsAdapter.notifyDataSetChanged();
                groupAdapter.notifyDataSetChanged();

//                groupPager.setCurrentItem(0);
            }
        } else Toast.makeText(this, "Group is not empty", Toast.LENGTH_SHORT).show();

    }

    public void onAddItem(View v) {
        EditText etNewItem = groupPager.getChildAt(groupPager.getCurrentItem()).findViewById(R.id.et_new_item);
        String textItem = etNewItem.getText().toString();

        items = groupItems.get(groupPager.getCurrentItem()).getItems();

        items.add(new ToDoDataModel(textItem, false));
        lvItems = groupPager.getChildAt(groupPager.getCurrentItem()).findViewById(R.id.lv_items);
        itemsAdapter = new ToDoAdapter(groupPager.getContext(), items, this::deleteItem, this::strikeoutListViewItemListener);
        lvItems.setAdapter(itemsAdapter);

        etNewItem.setText("");
    }
}

