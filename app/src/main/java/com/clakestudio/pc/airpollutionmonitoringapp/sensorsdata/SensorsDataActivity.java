package com.clakestudio.pc.airpollutionmonitoringapp.sensorsdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clakestudio.pc.airpollutionmonitoringapp.R;

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


        SensorsDataFragment sensorsDataFragment = (SensorsDataFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (sensorsDataFragment==null) {
            sensorsDataFragment = sensorsDataFragmentLazy.get();
        }


    }
}
