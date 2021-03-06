package com.example.android.inventoryapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Alexa on 8/10/2016.
 */
public class ItemAdapter extends ArrayAdapter<Item> {

    private Button decrementButton;
    private DatabaseHelper db;

    public ItemAdapter(Context context, List<Item> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.inven_list_item, parent, false);
        }

        db = new DatabaseHelper(getContext());
        final Item currentItem = getItem(position);

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detail = new Intent(getContext(), DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("key",currentItem);
                detail.putExtras(bundle);
                getContext().startActivity(detail);
            }
        });

        TextView nameView = (TextView) listItemView.findViewById(R.id.item_name);
        nameView.setText("Item: " + currentItem.getName());

        final TextView quantityView = (TextView) listItemView.findViewById(R.id.item_quantity);
        quantityView.setText("Quantity: "+currentItem.getQuantity());

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        TextView priceView = (TextView) listItemView.findViewById(R.id.item_price);
        priceView.setText("Price: "+currencyFormatter.format(currentItem.getPrice()));
        decrementButton = (Button) listItemView.findViewById(R.id.decrement_listview);

        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentItem.getQuantity() > 0) {
                    currentItem.setQuantity(currentItem.getQuantity() -1);
                    quantityView.setText("Quantity: " + currentItem.getQuantity());
                    db.updateData(currentItem.getId() ,currentItem.getName(),
                            currentItem.getQuantity(), currentItem.getPrice(), currentItem.getImagePath());
                }
            }
        });

        return listItemView;
    }
}