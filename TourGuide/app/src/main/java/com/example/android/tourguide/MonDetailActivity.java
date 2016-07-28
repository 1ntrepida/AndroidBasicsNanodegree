package com.example.android.tourguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Alexa on 7/27/2016.
 */
public class MonDetailActivity extends ActionBarActivity {

    Location value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        Bundle bundle = new Bundle();
        for (String key : bundle.keySet()) {
            value = (Location) bundle.getSerializable(key);
        }
        value.getImageId();
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