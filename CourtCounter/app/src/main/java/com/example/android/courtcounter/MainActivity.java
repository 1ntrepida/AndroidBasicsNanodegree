package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int playerOne = 0;
    int playerTwo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playerOnePlusOne(View view) {
        playerOne++;
        displayP1(playerOne);
    }

    public void playerOnePlusTwo(View view) {
        playerOne += 2;
        displayP1(playerOne);
    }

    public void playerOnePlusThree(View view) {
        playerOne += 3;
        displayP1(playerOne);
    }

    public void playerTwoPlusOne(View view) {
        playerTwo++;
        displayP2(playerTwo);
    }

    public void playerTwoPlusTwo(View view) {
        playerTwo += 2;
        displayP2(playerTwo);
    }

    public void playerTwoPlusThree(View view) {
        playerTwo += 3;
        displayP2(playerTwo);
    }

    private void displayP1(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.player1);
        quantityTextView.setText("" + number);
    }

    private void displayP2(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.player2);
        quantityTextView.setText("" + number);
    }

    public void reset(View view) {
        playerOne = 0;
        playerTwo = 0;
        displayP1(playerOne);
        displayP2(playerTwo);
    }

}

