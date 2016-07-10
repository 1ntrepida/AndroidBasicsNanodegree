package com.example.android.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button searchMusic = (Button) findViewById(R.id.search_music);

        // Set a click listener on that View
        searchMusic.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                // Start the new activity
                startActivity(searchIntent);
            }
        });

        Button libraryMusic = (Button) findViewById(R.id.library_music);

        // Set a click listener on that View
        libraryMusic.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent buyIntent = new Intent(MainActivity.this, ListMusicActivity.class);

                // Start the new activity
                startActivity(buyIntent);
            }
        });
    }
}
