package com.example.android.newsfeedapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
    public View getView(int position, View listItemView, ViewGroup parent) {
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        final Article currentNews = getItem(position);

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent web = new Intent(Intent.ACTION_VIEW);
                web.setData(Uri.parse(currentNews.getUrl()));
                getContext().startActivity(web);
            }
        });

        TextView titleView = (TextView) listItemView.findViewById(R.id.headline);
        titleView.setText(currentNews.getHeadline());
        return listItemView;
    }
}