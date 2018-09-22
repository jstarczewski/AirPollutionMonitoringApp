package com.clakestudio.pc.airpollutionmonitoringapp.sensorsdata;

import android.os.Bundle;

import com.clakestudio.pc.airpollutionmonitoringapp.R;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BaseActivity;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class SensorsDataActivity extends DaggerAppCompatActivity {

    @Inject
    SensorsDataPresenter presenter;

    @Inject
    Lazy<SensorsDataFragment> sensorsDataFragmentLazy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors_data);


        SensorsDataFragment sensorsDataFragment = (SensorsDataFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (sensorsDataFragment == null) {
            sensorsDataFragment = sensorsDataFragmentLazy.get();
            BaseActivity.addFragmentToActivity(getSupportFragmentManager(), sensorsDataFragment, R.id.contentFrame);
        }

    }
}
