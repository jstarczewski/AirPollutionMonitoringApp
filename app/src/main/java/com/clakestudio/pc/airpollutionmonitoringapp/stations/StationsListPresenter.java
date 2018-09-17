package com.clakestudio.pc.airpollutionmonitoringapp.stations;

import android.util.Log;

import com.clakestudio.pc.airpollutionmonitoringapp.data.AirPollutionDataSourceInterface;
import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.StationDataModel;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BasePresenter;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BaseSchedulerProvider;
import com.clakestudio.pc.airpollutionmonitoringapp.viewmodels.ListViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Jan on 9/17/2018.
 */

public class StationsListPresenter implements StationsListContract.Presenter {

    private StationsListContract.View view;
    private AirPollutionDataSourceInterface airPollutionDataSourceInterface;
    private CompositeDisposable compositeDisposable;
    private BaseSchedulerProvider baseSchedulerProvider;

    StationsListPresenter(StationsListContract.View view, AirPollutionDataSourceInterface airPollutionDataSourceInterface, BaseSchedulerProvider baseSchedulerProvider) {
        this.view = view;
        this.airPollutionDataSourceInterface = airPollutionDataSourceInterface;
        this.compositeDisposable = new CompositeDisposable();
        this.baseSchedulerProvider = baseSchedulerProvider;
        view.setPresenter(this);
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

        Log.e("elo", "here I am 0");
        compositeDisposable.add(
                airPollutionDataSourceInterface.getStations().observeOn(baseSchedulerProvider.getUIScheduler())
                .startWith(ListViewModel.loading()
                )
                .onErrorReturn(
                        new Function<Throwable, ListViewModel>() {
                            @Override
                            public ListViewModel apply(Throwable throwable) throws Exception {
                                return ListViewModel.error(throwable.getMessage());
                            }
                        }
                )
                .subscribeWith(new DisposableSubscriber<ListViewModel>() {

                    @Override
                    public void onNext(ListViewModel uiListViewModel) {
                        if (uiListViewModel.isHasError()) {
                            //view.showErrorMessage(uiListViewModel.getErrorMessage())
                            view.showStartSensorsListActivity();
                            Log.e("elo", "here I am error");
                        }
                        else if (uiListViewModel.isLoading()) {
                            //view.showLoadingIndicator();
                            Log.e("elo", "here I am loding");
                        }
                        else {
                            Log.e("elo", "here I am");
                            Log.e("list", uiListViewModel.getStationDataModels().toString());
                            view.showStationList((ArrayList<StationDataModel>) uiListViewModel.getStationDataModels());
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
    public void startSensorsListActivity() {

    }
}
