package com.example.android.inventoryapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView itemsListView;
    private ArrayList<Item> items;
    private Button addItem;
    private EditText name, quantity, price;
    private Cursor res;
    public DatabaseHelper db;
    private int isInserted;
    private ListView itemListView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);

        addItem = (Button) findViewById(R.id.create_new_item);
        name = (EditText) findViewById(R.id.name);
        quantity = (EditText) findViewById(R.id.quantity);
        price = (EditText) findViewById(R.id.price);

        res = db.getAllData();

        if (res == null) {
            TextView view = (TextView) findViewById(R.id.empty_view);
            view.setVisibility(View.VISIBLE);
            itemsListView.setEmptyView(view);
        }
        itemListView = (ListView) findViewById(R.id.list);

        items = new ArrayList<Item>();
        updateUI(items);

        addItem.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           String add_name = name.getText().toString();
                                           int add_quantity = 0;
                                           double add_price = 0;
                                           try {
                                               add_quantity = Integer.parseInt(quantity.getText().toString());
                                               add_price = Double.parseDouble(price.getText().toString());
                                           } catch (Exception e) {
                                               // TODO Handle the Exception
                                           }
                                           boolean valid = validateInfo(add_name, add_quantity, add_price);
                                           isInserted = -1;
                                           if (valid) {
                                               isInserted = db.insert(add_name, add_quantity, add_price);
                                           }

                                           if (isInserted > 0) {
                                               Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_LONG).show();

                                           } else {
                                               Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                                           }
                                           items = parseAllData();
                                           updateUI(items);

                                           Intent camera = new Intent();
                                           camera.setAction("android.media.action.IMAGE_CAPTURE");
                                           camera.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                           getApplicationContext().startActivity(camera);
                                       }
                                   }
        );
    }

    public boolean validateInfo(String name, int quantity, double price) {
        if (name.equals("") || quantity <= 0 || price <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Item> parseAllData() {
        res = db.getAllData();
        items.clear();
        if (res.getCount() == 0) {
            return null;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            Item newItem = new Item(res.getString(1), res.getInt(2), res.getDouble(3));
            newItem.setId(isInserted);
            items.add(newItem);
        }
        res.close();
        return items;
    }

    public void updateUI(ArrayList<Item> items) {
        this.items = items;
        ItemAdapter adapter = new ItemAdapter(this, items);
        itemListView.setAdapter(adapter);
    }

}
