package com.example.android.tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the View that shows the numbers category
        TextView one = (TextView) findViewById(R.id.oneLocation);

        // Set a click listener on that View
        one.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent oneIntent = new Intent(MainActivity.this, RestaurantActivity.class);

                // Start the new activity
                startActivity(oneIntent);
            }
        });

        TextView two = (TextView) findViewById(R.id.twoLocation);

        // Set a click listener on that View
        two.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent twoIntent = new Intent(MainActivity.this, MonumentActivity.class);

                // Start the new activity
                startActivity(twoIntent);
            }
        });

        TextView three = (TextView) findViewById(R.id.threeLocation);

        // Set a click listener on that View
        three.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent threeIntent = new Intent(MainActivity.this, StationActivity.class);

                // Start the new activity
                startActivity(threeIntent);
            }
        });

        TextView four = (TextView) findViewById(R.id.fourLocation);
        // i am on vacation
        // give me a break
        // Set a click listener on that View
        four.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent fourIntent = new Intent(MainActivity.this, ParkActivity.class);

                // Start the new activity
                startActivity(fourIntent);
            }
        });
    }

}
