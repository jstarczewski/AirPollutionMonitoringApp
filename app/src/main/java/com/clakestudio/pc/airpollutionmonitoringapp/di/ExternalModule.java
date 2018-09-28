package com.clakestudio.pc.airpollutionmonitoringapp.di;

import android.content.Context;

import com.clakestudio.pc.airpollutionmonitoringapp.AirPollutionApplication;
import com.clakestudio.pc.airpollutionmonitoringapp.data.AirPollutionDataSourceImplementation;
import com.clakestudio.pc.airpollutionmonitoringapp.data.AirPollutionDataSourceInterface;
import com.clakestudio.pc.airpollutionmonitoringapp.data.AirPollutionRestAdapter;
import com.clakestudio.pc.airpollutionmonitoringapp.data.URLManager;
import com.clakestudio.pc.airpollutionmonitoringapp.error.ErrorInterceptor;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BaseSchedulerProvider;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.SchedulerProvider;
import com.squareup.moshi.Moshi;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

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
public class ExternalModule {


    @Provides
    Context provideContext(AirPollutionApplication application){
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    AirPollutionDataSourceInterface provideDataSource(AirPollutionRestAdapter adapter){
        return new AirPollutionDataSourceImplementation(adapter);
    }

    @Provides
    @Singleton
    AirPollutionRestAdapter provideRestAdapter(Retrofit retrofit){
        return new AirPollutionRestAdapter(retrofit);
    }


    @Provides
    @Singleton
    Retrofit provideRetrofit(){
        OkHttpClient client =
                new OkHttpClient.Builder()
                        .addInterceptor(
                                new ErrorInterceptor()
                        )
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)
                        .build();

        return new Retrofit.Builder()
                .client(client)
                .baseUrl(URLManager.API_HOST)
            //    .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    BaseSchedulerProvider providerScheduler(){
        return new SchedulerProvider();
    }


}
