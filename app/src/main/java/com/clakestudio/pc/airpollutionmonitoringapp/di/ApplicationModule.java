package com.clakestudio.pc.airpollutionmonitoringapp.di;

import android.app.Application;
import android.content.Context;

import com.clakestudio.pc.airpollutionmonitoringapp.AirPollutionApplication;
import com.clakestudio.pc.airpollutionmonitoringapp.data.AirPollutionDataSourceImplementation;
import com.clakestudio.pc.airpollutionmonitoringapp.data.AirPollutionDataSourceInterface;
import com.clakestudio.pc.airpollutionmonitoringapp.data.AirPollutionRestAdapter;
import com.clakestudio.pc.airpollutionmonitoringapp.data.URLManager;
import com.clakestudio.pc.airpollutionmonitoringapp.error.ErrorInterceptor;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BaseSchedulerProvider;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.SchedulerProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Jan on 9/17/2018.
 */

@Module
public abstract class ApplicationModule {

    @Binds
    abstract Context bindContext(Application application);


}
