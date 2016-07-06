package com.example.android.popculturequiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void determineWinner(View view) {
        countPoints();
        displayPoints();
    }

    private void countPoints(){
        CheckBox checkBox = (CheckBox) findViewById(R.id.bad);
        boolean firstQuestion_answerOne = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.theydontcareaboutus);
        boolean firstQuestion_answerTwo = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.maninthemirror);
        boolean firstQuestion_answerThree = checkBox.isChecked();
        if(firstQuestion_answerOne && (!firstQuestion_answerTwo) && (!firstQuestion_answerThree)) {
            points++;
        }

        checkBox = (CheckBox) findViewById(R.id.q11);
        boolean secondQuestion_answerOne = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.q12);
        boolean secondQuestion_answerTwo = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.q13);
        boolean secondQuestion_answerThree = checkBox.isChecked();
        if((!secondQuestion_answerOne) && secondQuestion_answerTwo && (!secondQuestion_answerThree)) {
            points++;
        }

        EditText editText = (EditText) findViewById(R.id.inputText);
        String text = editText.getText().toString();
        if(text.equals("Earth Song")) {
            points++;
        }

        //radio buttons

        checkBox = (CheckBox) findViewById(R.id.leavemealone);
        boolean fifthQuestion_answerOne = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.thewaytoymakemefeel);
        boolean fifthQuestion_answerTwo = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.pyt);
        boolean fifthQuestion_answerThree = checkBox.isChecked();
        if((!secondQuestion_answerOne) && secondQuestion_answerTwo && (!secondQuestion_answerThree)) {
            points++;
        }


    }

    private void displayPoints() {
        TextView result = (TextView) findViewById(R.id.result);
        result.setText("You got "+ points + " out of 5 correct!");
    }

}
