package com.clakestudio.pc.airpollutionmonitoringapp.sensors;

import com.clakestudio.pc.airpollutionmonitoringapp.di.ActivityScoped;
import com.clakestudio.pc.airpollutionmonitoringapp.di.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Jan on 9/21/2018.
 */

@Module
public abstract class SensorsListModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SensorsListFragment sensorsListFragment();

    @ActivityScoped
    @Binds
    abstract SensorsListContract.Presenter sensrosListPresenter(SensorsListPresenter presenter);

}
