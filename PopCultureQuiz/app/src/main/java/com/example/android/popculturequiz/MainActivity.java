package com.example.android.popculturequiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int mPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void determineWinner(View view) {
        countPoints();
        displayPoints();
    }

    private void countPoints() {
        CheckBox checkBox = (CheckBox) findViewById(R.id.michaeljackson);
        boolean firstQuestion_answerOne = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.martinscorsese);
        boolean firstQuestion_answerTwo = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.alsharpton);
        boolean firstQuestion_answerThree = checkBox.isChecked();
        if (firstQuestion_answerOne && firstQuestion_answerTwo && (!firstQuestion_answerThree)) {
            mPoints++;
        }

        RadioButton five = (RadioButton) findViewById(R.id.q22);
        if (five.isChecked()) {
            mPoints++;
        }

        EditText editText = (EditText) findViewById(R.id.inputText);
        String text = editText.getText().toString();
        if (text.equalsIgnoreCase("Earth Song")) {
            mPoints++;
        }

        RadioButton llama = (RadioButton) findViewById(R.id.yesllama);
        if (llama.isChecked()) {
            mPoints++;
        }

        checkBox = (CheckBox) findViewById(R.id.rihanna);
        boolean fifthQuestion_answerOne = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.kanye);
        boolean fifthQuestion_answerTwo = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.lilwayne);
        boolean fifthQuestion_answerThree = checkBox.isChecked();
        if (fifthQuestion_answerOne && (!fifthQuestion_answerTwo) && fifthQuestion_answerThree) {
            mPoints++;
        }
    }

    private void displayPoints() {
        String result;
        if (mPoints == 5) {
            result = "You got all of them right! Shamone!";
        } else {
            result = "You got " + mPoints + " out of 5 correct!";
        }
        Toast toast = Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG);
        toast.show();
    }
}
