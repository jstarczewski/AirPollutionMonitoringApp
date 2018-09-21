package com.clakestudio.pc.airpollutionmonitoringapp.sensors;

import com.clakestudio.pc.airpollutionmonitoringapp.data.AirPollutionDataSourceInterface;
import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.SensorDataModel;
import com.clakestudio.pc.airpollutionmonitoringapp.di.ActivityScoped;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BaseSchedulerProvider;
import com.clakestudio.pc.airpollutionmonitoringapp.viewmodels.ListViewModelSensors;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Jan on 9/19/2018.
 */

@ActivityScoped
public class SensorsListPresenter implements SensorsListContract.Presenter {

    private SensorsListContract.View view;
    private AirPollutionDataSourceInterface dataSourceInterface;
    private BaseSchedulerProvider baseSchedulerProvider;
    private CompositeDisposable compositeDisposable;
    private String stationId;


    SensorsListPresenter(AirPollutionDataSourceInterface dataSourceInterface, BaseSchedulerProvider baseSchedulerProvider, String stationId) {
        this.dataSourceInterface = dataSourceInterface;
        this.baseSchedulerProvider = baseSchedulerProvider;
        this.compositeDisposable = new CompositeDisposable();
        this.stationId = stationId;
    }


    @Override
    public void takeView(SensorsListContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loadSensorsList() {
        compositeDisposable.add(
                dataSourceInterface.getSensors(this.stationId).observeOn(baseSchedulerProvider.getUIScheduler())
                .startWith(ListViewModelSensors.loading())
                .onErrorReturn(new Function<Throwable, ListViewModelSensors>() {
                    @Override
                    public ListViewModelSensors apply(Throwable throwable) throws Exception {
                        return ListViewModelSensors.error(throwable.getMessage());
                    }
                })
                .subscribeWith(new DisposableSubscriber<ListViewModelSensors>() {
                    @Override
                    public void onNext(ListViewModelSensors uiListViewModelSensors) {
                        if (uiListViewModelSensors.isHasError()) {
                            // handle this
                        } else if (uiListViewModelSensors.isLoading()) {
                            // handle that
                        } else {
                            view.showSensorsList((ArrayList<SensorDataModel>) uiListViewModelSensors.getSensorDataModels());
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


    @Override
    public void startSensorsDataActivity() {

    }
}
