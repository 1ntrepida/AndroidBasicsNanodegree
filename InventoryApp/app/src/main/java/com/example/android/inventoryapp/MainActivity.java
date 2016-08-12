package com.example.android.inventoryapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView itemsListView;
    private ArrayList<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<Item>();
        items.add(new Item("Gyarados", 420, 5.00));
        itemsListView = (ListView) findViewById(R.id.list);
        ItemAdapter adapter = new ItemAdapter(this, items);
        itemsListView.setAdapter(adapter);
    }

    public void minusQuantity(View view) {
        final int position = itemsListView.getPositionForView(view);
        Item numba = (Item) itemsListView.getItemAtPosition(position);
        int value = numba.setQuantity(numba.getQuantity()-1);

        TextView quantity = (TextView) findViewById(R.id.item_quantity);
        value--;
        quantity.setText(value);
    }
}
