package com.clakestudio.pc.airpollutionmonitoringapp.stations;

import com.clakestudio.pc.airpollutionmonitoringapp.di.ActivityScoped;
import com.clakestudio.pc.airpollutionmonitoringapp.di.FragmentScoped;
import com.clakestudio.pc.airpollutionmonitoringapp.stations.StationsListActivity;
import com.clakestudio.pc.airpollutionmonitoringapp.stations.StationsListContract;
import com.clakestudio.pc.airpollutionmonitoringapp.stations.StationsListFragment;
import com.clakestudio.pc.airpollutionmonitoringapp.stations.StationsListPresenter;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BaseView;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Jan on 9/17/2018.
 */

@Module
public abstract class StationsListModule {

//        @Binds
  //      abstract StationsListContract.View provideFeatureView(StationsListFragment stationsListFragment);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract StationsListFragment stationsListFragment();

    @ActivityScoped
    @Binds
    abstract StationsListContract.Presenter stationsListPresenter(StationsListPresenter presenter);

}
