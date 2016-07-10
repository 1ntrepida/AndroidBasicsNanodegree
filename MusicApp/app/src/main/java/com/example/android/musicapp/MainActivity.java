package com.example.android.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nowPlaying(View view) {
        TextView nowPlaying = (TextView) findViewById(R.id.now_playing);

        // Set a click listener on that View
        nowPlaying.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent numbersIntent = new Intent(MainActivity.this, NowPlayingActivity.class);

                // Start the new activity
                startActivity(numbersIntent);
                int x = 0;

            }
        });
    }

    public void searchMusic(View view) {
        TextView searchMusic = (TextView) findViewById(R.id.search_music);

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
    }

    public void buyMusic(View view) {
        TextView searchMusic = (TextView) findViewById(R.id.buy_music);

        // Set a click listener on that View
        searchMusic.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent buyIntent = new Intent(MainActivity.this, BuyActivity.class);

                // Start the new activity
                startActivity(buyIntent);
            }
        });
    }

    public void listArtistMusic(View view) {
        TextView searchMusic = (TextView) findViewById(R.id.artist_music);

        // Set a click listener on that View
        searchMusic.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent buyIntent = new Intent(MainActivity.this, ListByArtistActivity.class);

                // Start the new activity
                startActivity(buyIntent);
            }
        });
    }
}
