package com.clakestudio.pc.airpollutionmonitoringapp.stations;

import android.os.Bundle;

import com.clakestudio.pc.airpollutionmonitoringapp.R;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BaseActivity;

public class StationsListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations_list);

        StationsListFragment stationsListFragment = (StationsListFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (stationsListFragment==null) {
            stationsListFragment = StationsListFragment.newInstance();
            addFragmentToActivity(getSupportFragmentManager(), stationsListFragment, R.id.contentFrame);
        }

    }
}
