package com.example.android.tourguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by arckw on 7/28/2016.
 */
public class ResDetailActivity extends ActionBarActivity {

    Location value;
    int imageId;
    String name;
    String address;
    String desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        Bundle bundle = getIntent().getExtras();
        value = (Location) bundle.getParcelable(getString(R.string.locationId));
        imageId = value.getImageId();
        name = value.getName();
        address = value.getAddress();
        desc = value.getDesc();

        ImageView imageView = (ImageView) findViewById(R.id.locationImage);
        imageView.setImageResource(imageId);

        TextView textView = (TextView) findViewById(R.id.locationName);
        textView.setText(name);

        textView = (TextView) findViewById(R.id.locationAddress);
        textView.setText(address);

        textView = (TextView) findViewById(R.id.locationDesc);
        textView.setText(desc);

    }

    /**
     * +     * A placeholder fragment containing a simple view.
     * +
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.location_detail, container, false);
            return rootView;
        }
    }
}