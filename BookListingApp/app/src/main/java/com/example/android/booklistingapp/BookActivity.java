package com.example.android.booklistingapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private ListView bookListView;
    private TextView howto;
    private Boolean changedStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);
        changedStatus = true;

        books = new ArrayList<Book>();
        if(savedInstanceState == null || !savedInstanceState.containsKey("key")) {
        }
        else {
            books = savedInstanceState.getParcelableArrayList("key");
        }

        howto = (TextView) findViewById(R.id.howTo);
        bookListView = (ListView) findViewById(R.id.list);
        bookListView.setEmptyView(howto);
        BookAdapter adapter = new BookAdapter(this, books);
        bookListView.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("key", books);
        super.onSaveInstanceState(outState);
    }

    private void updateUi(ArrayList<Book> books) {
        this.books = books;
        ListView bookListView = (ListView) findViewById(R.id.list);
        BookAdapter adapter = new BookAdapter(this, books);
        bookListView.setAdapter(adapter);
    }

    private void showHowTo() {
        howto.setVisibility(View.VISIBLE);
        bookListView.setEmptyView(getCurrentFocus());
    }

    public void refreshInfo(View view) {
        EditText title = (EditText) findViewById(R.id.query);
        String titleInfo = title.getText().toString();
        if (titleInfo.equals("")) {
            showHowTo();
        } else {
            titleInfo = titleInfo.replaceAll(" ", "+").toLowerCase();
            requestUrl = "https://www.googleapis.com/books/v1/volumes?q=" + titleInfo + "&maxResults=10";
            TextView howto = (TextView) findViewById(R.id.howTo);
            howto.setVisibility(View.INVISIBLE);
            BookAsyncTask task = new BookAsyncTask();
            task.execute();
        }
        ConnectivityManager cm =
                (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        Toast toast;
        if(isConnected){
            toast = Toast.makeText(getApplicationContext(), "Connected to the internet!",  Toast.LENGTH_SHORT);
        }
        else {
            toast = Toast.makeText(getApplicationContext(), "You are not connected to the internet!",  Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    private class BookAsyncTask extends AsyncTask<URL, Void, ArrayList<Book>> {

        @Override
        protected ArrayList<Book> doInBackground(URL... urls) {
            URL url = createUrl(requestUrl);

            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);

            } catch (IOException e) {
                // TODO Handle the IOException
            }

            ArrayList<Book> books = extractFeatureFromJson(jsonResponse);
            return books;
        }

        @Override
        protected void onPostExecute(ArrayList<Book> books) {
            if (books == null) {
                Toast.makeText(getApplicationContext(), "You don't have any results! Please type in a keyword then hit the \"Refresh\" button.", Toast.LENGTH_LONG).show();
                return;
            }
            updateUi(books);
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

        private ArrayList<Book> extractFeatureFromJson(String bookJSON) {
            try {
                JSONObject baseJsonResponse = new JSONObject(bookJSON);
                JSONArray featureArray = baseJsonResponse.getJSONArray("items");

                ArrayList<Book> fetchedBooks = new ArrayList<Book>();
                if (featureArray.length() > 0) {
                    for (int i = 0; i < featureArray.length(); i++) {
                        JSONObject firstFeature = featureArray.getJSONObject(i);
                        JSONObject properties = firstFeature.getJSONObject("volumeInfo");

                        // Extract out the title, time, and tsunami values
                        String title = properties.getString("title");
                        String author = properties.getString("authors");
                        author = (author.substring(2, author.length() - 2)).replaceAll("\"", "");

                        // Create a new {@link Event} object
                        fetchedBooks.add(new Book(title, author));
                    }
                    return fetchedBooks;
                }
            } catch (JSONException e) {
            }
            return null;
        }

    }
}
