package com.example.android.newsfeedapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Alexa on 8/6/2016.
 */
public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapter(Context context, List<Article> articles) {
        super(context, 0, articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        // Find the earthquake at the given position in the list of earthquakes
        Article currentNews = getItem(position);

        // Find the TextView with view ID magnitude
        TextView titleView = (TextView) listItemView.findViewById(R.id.headline);
        // Display the magnitude of the current earthquake in that TextView
        titleView.setText(currentNews.getHeadline());
        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }
}