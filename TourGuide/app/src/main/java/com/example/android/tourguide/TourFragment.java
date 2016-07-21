package com.example.android.tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alexa on 7/21/2016.
 */
public class TourFragment extends Fragment {

    private ArrayAdapter<String> mLocationAdapter;
    private ArrayList<Location> locations;

    public TourFragment(){
        locations = new ArrayList<Location>();
        locations.add(new Location(R.drawable.one ,"Times Square", "Epicenter of New York hustle and bustle."));
        locations.add(new Location(R.drawable.two ,"Museum of Modern Art", "Gorgeous collection of modern art."));
        locations.add(new Location(R.drawable.three ,"9/11 Memorial", "The monument paying tribute to the lives lost at 9/11/2001"));
        locations.add(new Location(R.drawable.four ,"Statue of Liberty", "The Statue of Liberty is a colossal neoclassical sculpture on Liberty Island in New York Harbor in New York City, in the United States."));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(Bundle savedInstanceState) {
        LocationAdapter adapter = new LocationAdapter();
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }

}
