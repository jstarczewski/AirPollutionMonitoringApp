package com.clakestudio.pc.airpollutionmonitoringapp.sensorsdata;

import com.clakestudio.pc.airpollutionmonitoringapp.di.ActivityScoped;
import com.clakestudio.pc.airpollutionmonitoringapp.di.FragmentScoped;
import com.clakestudio.pc.airpollutionmonitoringapp.stations.StationsListContract;
import com.clakestudio.pc.airpollutionmonitoringapp.stations.StationsListPresenter;

import butterknife.BindView;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Jan on 9/22/2018.
 */

@Module
public abstract class SensorsDataModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SensorsDataFragment sensorsDataFragment();

    @Binds
    @ActivityScoped
    abstract SensorsDataContract.Presenter sensorsDataPresneter(SensorsDataPresenter presenter);


}
