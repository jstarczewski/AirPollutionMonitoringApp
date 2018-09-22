package com.clakestudio.pc.airpollutionmonitoringapp.sensorsdata;

import com.clakestudio.pc.airpollutionmonitoringapp.data.AirPollutionDataSourceInterface;
import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.SensorsDataDataModel;
import com.clakestudio.pc.airpollutionmonitoringapp.di.ActivityScoped;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BaseSchedulerProvider;
import com.clakestudio.pc.airpollutionmonitoringapp.viewmodels.ListViewModelSensorsData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Jan on 9/22/2018.
 */

@ActivityScoped
public class SensorsDataPresenter implements SensorsDataContract.Presenter {

    private SensorsDataContract.View view;
    private CompositeDisposable compositeDisposable;
    private AirPollutionDataSourceInterface airPollutionDataSourceInterface;
    private BaseSchedulerProvider baseSchedulerProvider;

    @Inject
    public SensorsDataPresenter(AirPollutionDataSourceInterface airPollutionDataSourceInterface, BaseSchedulerProvider baseSchedulerProvider) {
        this.airPollutionDataSourceInterface = airPollutionDataSourceInterface;
        this.baseSchedulerProvider = baseSchedulerProvider;
        this.compositeDisposable = new CompositeDisposable();
    }


    @Override
    public void takeView(SensorsDataContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loadSensorsData(String sensorId) {

        compositeDisposable.add(airPollutionDataSourceInterface.getSensorsData(sensorId).observeOn(baseSchedulerProvider.getUIScheduler())
                .startWith(ListViewModelSensorsData.loading())
                .onErrorReturn(
                        new Function<Throwable, ListViewModelSensorsData>() {
                            @Override
                            public ListViewModelSensorsData apply(Throwable throwable) throws Exception {
                                return ListViewModelSensorsData.error(throwable.getMessage());
                            }
                        })
                .subscribeWith(new DisposableSubscriber<ListViewModelSensorsData>() {
                    @Override
                    public void onNext(ListViewModelSensorsData uiListViewModelSensorsData) {
                        if (uiListViewModelSensorsData.isHasError()) {

                        } else if (uiListViewModelSensorsData.isLoading()) {

                        } else {
                            view.showSensorsData((ArrayList<SensorsDataDataModel>) uiListViewModelSensorsData.getSensorsDataDataModels());
                        }

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                })
        );

    }
}
