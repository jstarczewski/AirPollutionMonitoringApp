package com.clakestudio.pc.airpollutionmonitoringapp.di;

import com.clakestudio.pc.airpollutionmonitoringapp.sensors.SensorsListActivity;
import com.clakestudio.pc.airpollutionmonitoringapp.sensors.SensorsListModule;
import com.clakestudio.pc.airpollutionmonitoringapp.stations.StationsListActivity;
import com.clakestudio.pc.airpollutionmonitoringapp.stations.StationsListModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Jan on 9/17/2018.
 */


@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = StationsListModule.class)
    abstract StationsListActivity stationsListActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = SensorsListModule.class)
    abstract SensorsListActivity sensorsListActivity();
}
