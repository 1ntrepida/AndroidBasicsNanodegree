package com.example.android.inventoryapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Item> items;
    private Button addItem, update;
    private EditText name, quantity, price;
    private Cursor res;
    public DatabaseHelper db;
    private int isInserted;
    private ListView itemListView;
    private ItemAdapter adapter;
    private TextView view;

    private static final int SELECT_SINGLE_PICTURE = 1;
    public static final String IMAGE_TYPE = "image/*";
    private String selectedImagePath, add_name;
    private int add_quantity;
    private double add_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        selectedImagePath = "";

        itemListView = (ListView) findViewById(R.id.list);
        addItem = (Button) findViewById(R.id.create_new_item);
        update = (Button) findViewById(R.id.updateUI);
        name = (EditText) findViewById(R.id.name);
        quantity = (EditText) findViewById(R.id.quantity);
        price = (EditText) findViewById(R.id.price);
        adapter = new ItemAdapter(this, items);

        res = db.getAllData();
        items = new ArrayList<Item>();

        adapter = new ItemAdapter(this, items);
        itemListView.setAdapter(adapter);
        add_name = "";
        add_quantity = 0;
        add_price = 0;

        //initializes on empty view
        setAppEmptyView(items.size());

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_name = name.getText().toString();
                try {
                    add_quantity = Integer.parseInt(quantity.getText().toString());
                    add_price = Double.parseDouble(price.getText().toString());
                } catch (Exception e) {
                    // TODO Handle the Exception
                }
                boolean valid = validateInfo(add_name, add_quantity, add_price);
                isInserted = -1;
                if (valid) {
                    Intent intent = new Intent();
                    intent.setType(IMAGE_TYPE);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_SINGLE_PICTURE);
                }
                items = parseAllData();
                updateUI(items);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items = parseAllData();
                updateUI(items);
            }
        });
    }


    public boolean validateInfo(String name, int quantity, double price) {
        if (name.equals("") || quantity <= 0 || price <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_SINGLE_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = selectedImageUri.toString();
                isInserted = db.insert(add_name, add_quantity, add_price, selectedImagePath);
            }
        }
    }

    public ArrayList<Item> parseAllData() {
        res = db.getAllData();
        items.clear();
        if (res.getCount() == 0) {
            return new ArrayList<Item>();
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            Item newItem = new Item(res.getString(1), res.getInt(2), res.getDouble(3), res.getString(4));
            newItem.setId(isInserted);
            items.add(newItem);
        }
        res.close();
        return items;
    }

    public void setAppEmptyView(int size) {
        view = (TextView) findViewById(R.id.empty_view);
        if (items.size() == 0) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
        itemListView.setEmptyView(view);
    }

    public void updateUI(ArrayList<Item> items) {
        this.items = items;
        adapter = new ItemAdapter(this, items);
        itemListView.setAdapter(adapter);
        setAppEmptyView(items.size());
    }
}
