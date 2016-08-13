package com.example.android.inventoryapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView itemsListView;
    private ArrayList<Item> items;
    private Button addItem;
    private EditText name, quantity, price;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);

        addItem = (Button) findViewById(R.id.create_new_item);
        name = (EditText) findViewById(R.id.name);
        quantity = (EditText) findViewById(R.id.quantity);
        price = (EditText) findViewById(R.id.price);

        items = new ArrayList<Item>();
        items.add(new Item("Gyarados", 420, 5.00));
        itemsListView = (ListView) findViewById(R.id.list);
        ItemAdapter adapter = new ItemAdapter(this, items);
        itemsListView.setAdapter(adapter);
        addData();
    }

    public void addData(){
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = db.insert(name.getText().toString(), Integer.parseInt((String)quantity.getText().toString())
                        , Double.parseDouble(price.getText().toString()));
                if (isInserted == true){
                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    /** public void minusQuantity(View view) {
        final int position = itemsListView.getPositionForView(view);
        Item numba = (Item) itemsListView.getItemAtPosition(position);
        int value = numba.setQuantity(numba.getQuantity()-1);

        TextView quantity = (TextView) findViewById(R.id.item_quantity);
        value--;
        quantity.setText(value);
    } **/
}
