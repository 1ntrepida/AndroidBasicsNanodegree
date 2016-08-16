package com.example.android.inventoryapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;

public class DetailActivity extends ActionBarActivity {

    private Button deleteButton;
    private Button decrementButton;
    private Button incrementButton;
    private DatabaseHelper db;
    private Item focus;
    private TextView quantityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        db = new DatabaseHelper(getApplicationContext());

        deleteButton = (Button) findViewById(R.id.delete);
        decrementButton = (Button) findViewById(R.id.decrement);
        incrementButton = (Button) findViewById(R.id.increment);

        Bundle bundle = getIntent().getExtras();
        focus = bundle.getParcelable("key");

        ImageView imageView = (ImageView) findViewById(R.id.image_detail);
        imageView.setImageURI(Uri.parse(focus.getImagePath()));

        TextView nameView = (TextView) findViewById(R.id.individual_item_name);
        nameView.setText("Item: " + focus.getName());

        quantityView = (TextView) findViewById(R.id.individual_item_quantity);
        quantityView.setText("Quantity: " + focus.getQuantity());

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        TextView priceView = (TextView) findViewById(R.id.individual_item_price);
        priceView.setText("Price: " + currencyFormatter.format(focus.getPrice()));

        deleteData();
        decrementData();
        incrementData();
    }

    private void changeQuantity(int newNum) {
        quantityView.setText("Quantity: " + newNum);
        db.updateData(focus.getId() ,focus.getName(), focus.getQuantity(), focus.getPrice(), focus.getImagePath());
    }

    public void deleteData(){
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // this is where id use the delete function from the databasehelper class
                db.deleteItemsFromDatabase(focus.getName());
            }
        });
    }

    public void decrementData(){
        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(focus.getQuantity() > 0) {
                    focus.setQuantity(focus.getQuantity() -1);
                    changeQuantity(focus.getQuantity());
                }
                onBackPressed();
            }
        });
    }

    public void incrementData(){
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: ") /** **/);
                startActivity(intent);
            }
        });
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
