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
public class StationActivity extends AppCompatActivity {

    private ArrayList<Location> locations;
    private LocationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_list);

        locations = new ArrayList<Location>();
        locations.add(new Location(R.drawable.mon_one, getString(R.string.statueofliberty), getString(R.string.statueofliberynaddress), getString(R.string.statueoflibertydesc)));
        locations.add(new Location(R.drawable.mon_two, getString(R.string.grantstomb), getString(R.string.grantstombaddress), getString(R.string.granttombdesc)));
        locations.add(new Location(R.drawable.mon_three, getString(R.string.nineeleven), getString(R.string.nineelevenaddress), getString(R.string.nineelevendesc)));
        locations.add(new Location(R.drawable.mon_four, getString(R.string.grandarmy), getString(R.string.grandarmyaddress), getString(R.string.granttombdesc)));

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
                Bundle bundle = new Bundle();
                bundle.putParcelable(getString(R.string.locationId), item);
                startActivity(new Intent(getApplication(), StaDetailActivity.class).putExtras(bundle));
            }
        });
    }
}
