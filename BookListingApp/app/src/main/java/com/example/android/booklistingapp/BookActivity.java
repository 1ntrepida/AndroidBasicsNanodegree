package com.example.android.booklistingapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    private String requestUrl;
    private ArrayList<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);

        books = new ArrayList<Book>();
        books.add(new Book("To Kill A Mockingbird", "Harper Lee"));
        books.add(new Book("1984", "George Orwell"));
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald"));


        ListView bookListView = (ListView) findViewById(R.id.list);
        BookAdapter adapter = new BookAdapter(this, books);

        bookListView.setAdapter(adapter);
    }

    private void updateUi(Book book){
        books = new ArrayList<Book>();
        books.add(book);
        ListView bookListView = (ListView) findViewById(R.id.list);
        BookAdapter adapter = new BookAdapter(this, books);
        bookListView.setAdapter(adapter);
    }

    private void showHowTo(){
        TextView howto = (TextView) findViewById(R.id.howTo);
        howto.setText("Please type in the title of the book you want to find above!");
        howto.setVisibility(View.VISIBLE);
    }

    public void refreshInfo(View view) {
        EditText title = (EditText) findViewById(R.id.query);
        String titleInfo = title.getText().toString();
        if(titleInfo.equals("")){
            showHowTo();
        }
        else {
            titleInfo = titleInfo.replaceAll(" ", "+").toLowerCase();
            requestUrl = "https://www.googleapis.com/books/v1/volumes?q=" + titleInfo + "&maxResults=10";
            TextView howto = (TextView) findViewById(R.id.howTo);
            howto.setVisibility(View.INVISIBLE);
            BookAsyncTask task = new BookAsyncTask();
            task.execute();
        }
    }

    private class BookAsyncTask extends AsyncTask<URL, Void, Book> {

        @Override
        protected Book doInBackground(URL... urls) {
            URL url = createUrl(requestUrl);

            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);

            } catch (IOException e) {
                // TODO Handle the IOException
            }

            Book book = extractFeatureFromJson(jsonResponse);
            return book;
        }

        @Override
        protected void onPostExecute(Book book){
            if (book == null){
                return;
            }
            updateUi(book);
        }

        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                return null;
            }
            return url;
        }

        private String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } catch (IOException e) {
                // TODO: Handle the exception
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }
            return jsonResponse;
        }

        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }

        private Book extractFeatureFromJson(String bookJSON) {
            try {
                JSONObject baseJsonResponse = new JSONObject(bookJSON);
                JSONArray featureArray = baseJsonResponse.getJSONArray("items");

                // If there are results in the features array
                if (featureArray.length() > 0) {
                    // Extract out the first feature (which is an earthquake)
                    JSONObject firstFeature = featureArray.getJSONObject(0);
                    JSONObject properties = firstFeature.getJSONObject("volumeInfo");

                    // Extract out the title, time, and tsunami values
                    String title = properties.getString("title");
                    String author = properties.getString("authors");
                    author = (author.substring(2, author.length()-2)).replaceAll("\"", "");

                    // Create a new {@link Event} object
                    return new Book(title, author);
                }
            } catch (JSONException e) {
            }
            return null;
        }

    }
}
