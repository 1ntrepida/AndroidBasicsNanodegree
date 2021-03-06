package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view)
    {
        display(quantity);
        displayPrice(quantity*5);
        String thank= "Thank you for your purchase!";
        displayMessage(thank);
    }

    public void increment(View view)
    {
        quantity++;
        display(quantity);
    }

    public void decrement(View view)
    {
        quantity--;
        display(quantity);
    }

    private void display(int number)
    {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText(""+number);
    }

    private void displayPrice(int number){
        TextView priveTextView = (TextView) findViewById(R.id.price_text_view);
        priveTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private void displayMessage(String message)
    {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

}
