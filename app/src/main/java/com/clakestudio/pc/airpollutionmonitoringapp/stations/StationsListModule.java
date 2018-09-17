package com.clakestudio.pc.airpollutionmonitoringapp.stations;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Jan on 9/17/2018.
 */

@Module
public abstract class StationsListModule {

    @ContributesAndroidInjector
    abstract StationsListFragment stationsListFragment();

    @Binds
    abstract StationsListContract.Presenter stationsListPresenter(StationsListPresenter stationsListPresenter);

}
