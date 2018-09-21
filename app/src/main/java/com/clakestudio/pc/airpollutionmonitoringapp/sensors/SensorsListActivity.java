package com.clakestudio.pc.airpollutionmonitoringapp.sensors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clakestudio.pc.airpollutionmonitoringapp.R;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BaseActivity;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class SensorsListActivity extends DaggerAppCompatActivity {


    @Inject
    SensorsListPresenter sensorsListPresenter;

    @Inject
    Lazy<SensorsListFragment> sensorsListFragmentLazy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesnsors_list);

        SensorsListFragment sensorsListFragment = (SensorsListFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (sensorsListFragment==null) {
            sensorsListFragment = sensorsListFragmentLazy.get();
            BaseActivity.addFragmentToActivity(getSupportFragmentManager(), sensorsListFragment, R.id.contentFrame);
        }

    }
}
