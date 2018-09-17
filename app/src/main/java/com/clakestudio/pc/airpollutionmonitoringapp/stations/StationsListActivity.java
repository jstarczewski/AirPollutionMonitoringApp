package com.clakestudio.pc.airpollutionmonitoringapp.stations;

import android.app.Activity;
import android.os.Bundle;

import com.clakestudio.pc.airpollutionmonitoringapp.R;
import com.clakestudio.pc.airpollutionmonitoringapp.data.AirPollutionDataSourceImplementation;
import com.clakestudio.pc.airpollutionmonitoringapp.data.AirPollutionRestAdapter;
import com.clakestudio.pc.airpollutionmonitoringapp.data.URLManager;
import com.clakestudio.pc.airpollutionmonitoringapp.error.ErrorInterceptor;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BaseActivity;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.SchedulerProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.AndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.DaggerAppCompatActivity;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static com.clakestudio.pc.airpollutionmonitoringapp.utils.BaseActivity.addFragmentToActivity;

public class StationsListActivity extends DaggerAppCompatActivity {

    @Inject
    StationsListPresenter stationsListPresenter;

    @Inject
    Lazy<StationsListFragment> stationsListFragmentLazy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations_list);

        StationsListFragment stationsListFragment = (StationsListFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (stationsListFragment == null) {
            stationsListFragment = stationsListFragmentLazy.get();
            BaseActivity.addFragmentToActivity(getSupportFragmentManager(), stationsListFragment, R.id.contentFrame);
        }

        /**
         *  Thats why I need to learn how tu use Dagger2
         *      -> a dependency injection is a must here because of a lot of duplications ;/
         *
         * */

/*
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new ErrorInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(URLManager.API_HOST)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        AirPollutionRestAdapter airPollutionRestAdapter = new AirPollutionRestAdapter(retrofit);
        AirPollutionDataSourceImplementation airPollutionDataSourceImplementation = new AirPollutionDataSourceImplementation(airPollutionRestAdapter);

        SchedulerProvider schedulerProvider = new SchedulerProvider();
*/
 //       StationsListPresenter stationsListPresenter = new StationsListPresenter(stationsListFragment, airPollutionDataSourceImplementation, schedulerProvider);


    }

}
