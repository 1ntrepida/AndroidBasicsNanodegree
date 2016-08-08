package com.example.android.newsfeedapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
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

public class MainActivity extends AppCompatActivity {

    private ListView newsListView;
    private ArrayList<Article> news;
    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private final String requestUrl = "http://content.guardianapis.com/search?q=saakashvili&api-key=fa37d8a5-26c5-4ccf-b93d-2f4be66dcd7f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);

        news = new ArrayList<Article>();
        news.add(new Article("welp", "welp"));
        news.add(new Article("welp", "welp"));
        news.add(new Article("welp", "welp"));
        newsListView = (ListView) findViewById(R.id.list);
        ArticleAdapter adapter = new ArticleAdapter(this, news);
        newsListView.setAdapter(adapter);

        NewsAsyncTask task = new NewsAsyncTask();
        task.execute();
    }

    private void updateUi(ArrayList<Article> news) {
        this.news = news;
        ListView bookListView = (ListView) findViewById(R.id.list);
        ArticleAdapter adapter = new ArticleAdapter(this, news);
        newsListView.setAdapter(adapter);
    }

    private class NewsAsyncTask extends AsyncTask<URL, Void, ArrayList<Article>>{

        URL url;

        @Override
        protected ArrayList<Article> doInBackground(URL... urls){
            url = createUrl(requestUrl);
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);

            } catch (IOException e) {
                // TODO Handle the IOException
                Log.e(LOG_TAG, "No response from the URL request.", e);
            }

            ArrayList<Article> news = extractFeatureFromJson(jsonResponse);
            return news;
        }

        @Override
        protected void onPostExecute(ArrayList<Article> news) {
            if (news == null) {
                return;
            }
            updateUi(news);
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
                Log.e(LOG_TAG, "Can't read from stream.", e);
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

        private ArrayList<Article> extractFeatureFromJson(String bookJSON) {
            try {
                JSONObject baseJsonResponse = new JSONObject(bookJSON).getJSONObject("response");
                JSONArray featureArray = baseJsonResponse.getJSONArray("results");

                ArrayList<Article> fetchedNews = new ArrayList<Article>();
                if (featureArray.length() > 0) {
                    for (int i = 0; i < featureArray.length(); i++) {
                        JSONObject feature = featureArray.getJSONObject(i);
                        // Extract out the title, time, and tsunami values
                        String webTitle = feature.getString("webTitle");
                        String webUrl = feature.getString("webUrl");
                        // Create a new {@link Event} object
                        fetchedNews.add(new Article(webTitle, webUrl));
                    }
                    return fetchedNews;
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Malformed article.", e);
            }
            return null;
        }

        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                Log.e(LOG_TAG, "Malformed URL.", exception);
                return null;
            }
            return url;
        }
    }
}
