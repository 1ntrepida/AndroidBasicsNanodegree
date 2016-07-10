package com.example.android.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Alexa on 7/10/2016.
 */
public class ListMusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
    }

    public void showDetailsMusic(View view) {
        TextView searchMusic = (TextView) findViewById(R.id.show_details_music);

        // Set a click listener on that View
        searchMusic.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent detailIntent = new Intent(ListMusicActivity.this, DetailActivity.class);

                // Start the new activity
                startActivity(detailIntent);
            }
        });
    }

    public void nowPlaying(View view) {
        TextView nowPlaying = (TextView) findViewById(R.id.now_playing);

        // Set a click listener on that View
        nowPlaying.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent playingIntent = new Intent(ListMusicActivity.this, NowPlayingActivity.class);

                // Start the new activity
                startActivity(playingIntent);

            }
        });
    }
}