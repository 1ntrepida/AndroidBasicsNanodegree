package com.example.android.booklistingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);

        ArrayList<String> books = new ArrayList<String>();
        books.add("To Kill A Mockingbird");
        books.add("1984");
        books.add("The Lord Of The Rings");
        books.add("The Catcher In The Rye");
        books.add("The Great Gatsby");
        books.add("Lord Of The Flies");
        books.add("To Kill A Mockingbird");
        books.add("1984");
        books.add("The Lord Of The Rings");
        books.add("The Catcher In The Rye");
        books.add("The Great Gatsby");
        books.add("Lord Of The Flies");
        books.add("To Kill A Mockingbird");
        books.add("1984");
        books.add("The Lord Of The Rings");
        books.add("The Catcher In The Rye");
        books.add("The Great Gatsby");
        books.add("Lord Of The Flies");


        ListView bookListView = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, books);

        bookListView.setAdapter(adapter);
    }
}
