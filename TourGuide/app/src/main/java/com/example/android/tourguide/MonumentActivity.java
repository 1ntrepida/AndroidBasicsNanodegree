package com.example.android.tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Alexa on 7/21/2016.
 */
public class MonumentActivity extends AppCompatActivity {

    private ArrayList<Location> locations;
    private LocationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_list);

        locations = new ArrayList<Location>();
        locations.add(new Location(R.drawable.mon_one, "Statue of Liberty", "Liberty Island, New York Harbor", "Sleek, bi-level setting for upscale New American fare with fixed price & tasting menus."));
        locations.add(new Location(R.drawable.mon_two, "Grant's Tomb", "Riverside Drive at 122nd St.", "Daniel Bouluds elegant French flagship where jackets are required & expense accounts come in handy."));
        locations.add(new Location(R.drawable.mon_three, "9/11 Memorial", "911 Greenwich St, New York, NY 10006", "Chef Thomas Keller's New American restaurant offers luxe fixed-price menus, with Central Park views."));
        locations.add(new Location(R.drawable.mon_four, "The Grand Army Plaza", "Flatbush Ave, Brooklyn, NY 11217", "Grand Army Plaza is a public plaza that comprises the northern corner and the main entrance of Prospect Park in the New York City borough of Brooklyn."));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        adapter = new LocationAdapter(this, locations);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){
                //change this to a string input
                //string item
                Location item = adapter.getItem(position);
                Intent intent = new Intent(getActivity(), MonDetailActivity.class).putExtra(Intent.EXTRA_TEXT, item);
                startActivity(intent);
            }
        });
    }
}
