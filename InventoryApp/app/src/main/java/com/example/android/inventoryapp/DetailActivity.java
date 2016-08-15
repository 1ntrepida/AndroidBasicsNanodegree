package com.example.android.inventoryapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * Created by Alexa on 8/13/2016.
 */
public class DetailActivity extends ActionBarActivity {

    Button deleteButton;
    Button decrementButton;
    Item focus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        deleteButton = (Button) findViewById(R.id.delete);

        Bundle bundle = getIntent().getExtras();
        focus = bundle.getParcelable("key");

        TextView nameView = (TextView) findViewById(R.id.individual_item_name);
        nameView.setText("Item: " + focus.getName());

        TextView quantityView = (TextView) findViewById(R.id.individual_item_quantity);
        quantityView.setText("Quantity: " + focus.getQuantity());

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        TextView priceView = (TextView) findViewById(R.id.individual_item_price);
        priceView.setText("Price: " + currencyFormatter.format(focus.getPrice()));

        /** when i add deleteData() here, the items in the listview are no longer clickable**/
    }

    public void deleteData(){
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // this is where id use the delete function from the databasehelper class
                DatabaseHelper modify = MainActivity.db;
                modify.deleteItemsFromDatabase(focus.getName());
                MainActivity.db = modify;
            }
        });
        finish();
    }

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            return null;
        }
    }
}
