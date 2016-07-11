package com.example.android.reportcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ReportCard mine = new ReportCard("Alexa Rockwell");
        mine.addCourses("System Fundamentals 2", 85, "Fall 2016");
        mine.addCourses("Theory of Computation", 92, "Fall 2016");
        mine.addCourses("Computer Networking", 97, "Fall 2016");
        String reportCard = mine.toString();

        TextView mainScreen = (TextView) findViewById(R.id.report_card);
        mainScreen.setText(reportCard);
    }
}
