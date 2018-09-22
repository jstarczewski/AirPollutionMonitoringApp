package com.clakestudio.pc.airpollutionmonitoringapp;





import com.clakestudio.pc.airpollutionmonitoringapp.di.DaggerApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by Jan on 9/17/2018.
 */

public class AirPollutionApplication extends DaggerApplication {


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().application(this).build();
    }
}
