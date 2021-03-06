package com.clakestudio.pc.airpollutionmonitoringapp.stations;

import android.util.Log;

import com.clakestudio.pc.airpollutionmonitoringapp.data.AirPollutionDataSourceInterface;
import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.StationDataModel;
import com.clakestudio.pc.airpollutionmonitoringapp.di.ActivityScoped;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BaseSchedulerProvider;
import com.clakestudio.pc.airpollutionmonitoringapp.viewmodels.ListViewModelStations;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Jan on 9/17/2018.
 */

@ActivityScoped
public class StationsListPresenter implements StationsListContract.Presenter {

    private StationsListContract.View view;
    private AirPollutionDataSourceInterface airPollutionDataSourceInterface;
    private CompositeDisposable compositeDisposable;
    private BaseSchedulerProvider baseSchedulerProvider;

    @Inject
    StationsListPresenter(AirPollutionDataSourceInterface airPollutionDataSourceInterface, BaseSchedulerProvider baseSchedulerProvider) {
        //    this.view = view;
        this.airPollutionDataSourceInterface = airPollutionDataSourceInterface;
        this.compositeDisposable = new CompositeDisposable();
        this.baseSchedulerProvider = baseSchedulerProvider;
    }

    @Override
    public void takeView(StationsListContract.View view) {
        this.view = view;
        start();
    }

    @Override
    public void dropView() {
        this.view = null;
    }

    @Override
    public void start() {
        loadStationList();
    }

    @Override
    public void stop() {

    }
    @Override
    public void loadStationList() {
        compositeDisposable.add(
                airPollutionDataSourceInterface.getStations().observeOn(baseSchedulerProvider.getUIScheduler())
                        .startWith(ListViewModelStations.loading()
                        )
                        .onErrorReturn(
                                new Function<Throwable, ListViewModelStations>() {
                                    @Override
                                    public ListViewModelStations apply(Throwable throwable) throws Exception {
                                        return ListViewModelStations.error(throwable.getMessage());
                                    }
                                }
                        )
                        .subscribeWith(new DisposableSubscriber<ListViewModelStations>() {

                            @Override
                            public void onNext(ListViewModelStations uiListViewModelStations) {
                                if (uiListViewModelStations.isHasError()) {
                                    //view.showErrorMessage(uiListViewModelStations.getErrorMessage())
                                } else if (uiListViewModelStations.isLoading()) {
                                    //view.showLoadingIndicator();
                                } else {
                                    view.showStationList((ArrayList<StationDataModel>) uiListViewModelStations.getStationDataModels());
                                }
                            }

                            @Override
                            public void onError(Throwable t) {
                                // error
                            }

                            @Override
                            public void onComplete() {
                            }
                        })
        );
    }

    @Override
    public void startSensorsListActivity(String stationId) {
        view.showStartSensorsListActivity(stationId);
    }

}
