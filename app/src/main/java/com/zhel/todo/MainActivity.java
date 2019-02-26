package com.zhel.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ToDoAdapter itemsAdapter;
    private ArrayList<ToDoDataModel> items;
    private ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        items = new ArrayList<>();

        lvItems = (ListView) findViewById(R.id.lvItems);
        itemsAdapter = new ToDoAdapter(this, items);

        lvItems.setAdapter(itemsAdapter);

        strikeoutListViewItemListener();
        deleteItem();
    }

    private void strikeoutListViewItemListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {

                        if (items.get(pos).isStrikeout())
                            items.get(pos).setStrikeout(false);
                        else items.get(pos).setStrikeout(true);

                        itemsAdapter.notifyDataSetChanged();

                        return true;
                    }

                });
    }

    private void deleteItem() {
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);
                itemsAdapter.notifyDataSetChanged();
            }
        });

    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String textItem = etNewItem.getText().toString();
        items.add(new ToDoDataModel(textItem, false));
        itemsAdapter.notifyDataSetChanged();

        etNewItem.setText("");
    }
}
