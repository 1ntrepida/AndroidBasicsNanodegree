package com.example.android.inventoryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Alexa on 8/10/2016.
 */
public class ItemAdapter extends ArrayAdapter<Item> {

    public ItemAdapter(Context context, List<Item> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.inven_list_item, parent, false);
        }

        Item currentItem = getItem(position);

        TextView nameView = (TextView) listItemView.findViewById(R.id.item_name);
        nameView.setText(currentItem.getName());

        TextView quantityView = (TextView) listItemView.findViewById(R.id.item_quantity);
        quantityView.setText(currentItem.getQuantity()+"");

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        TextView priceView = (TextView) listItemView.findViewById(R.id.item_price);
        priceView.setText(currencyFormatter.format(currentItem.getPrice()));

        return listItemView;
    }
}