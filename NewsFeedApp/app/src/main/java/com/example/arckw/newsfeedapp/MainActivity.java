package com.example.arckw.newsfeedapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private String requestUrl = "http://content.guardianapis.com/search?q=saakashvili&api-key=fa37d8a5-26c5-4ccf-b93d-2f4be66dcd7f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
