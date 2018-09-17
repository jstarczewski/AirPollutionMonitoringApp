package com.clakestudio.pc.airpollutionmonitoringapp.di;

import android.app.Application;

import com.clakestudio.pc.airpollutionmonitoringapp.AirPollutionApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Jan on 9/17/2018.
 */

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        ExternalModule.class,
        ActivityBindingModule.class
})
public interface ApplicationComponent extends AndroidInjector<AirPollutionApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        ApplicationComponent.Builder application(Application application);

        ApplicationComponent build();

    }

}
