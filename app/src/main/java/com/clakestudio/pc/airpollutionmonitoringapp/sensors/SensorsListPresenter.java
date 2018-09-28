package com.clakestudio.pc.airpollutionmonitoringapp.sensors;

import android.util.Log;

import com.clakestudio.pc.airpollutionmonitoringapp.data.AirPollutionDataSourceInterface;
import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.SensorDataModel;
import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.SensorsDataDataModel;
import com.clakestudio.pc.airpollutionmonitoringapp.di.ActivityScoped;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BaseSchedulerProvider;
import com.clakestudio.pc.airpollutionmonitoringapp.viewmodels.ListViewModelSensors;
import com.clakestudio.pc.airpollutionmonitoringapp.viewmodels.ViewModelSensorsData;

import java.util.ArrayList;

import javax.inject.Inject;

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
    private String stationId = "-1";
    private ArrayList<SensorDataModel> sensorDataModels;

    @Inject
    SensorsListPresenter(AirPollutionDataSourceInterface dataSourceInterface, BaseSchedulerProvider baseSchedulerProvider) {
        this.dataSourceInterface = dataSourceInterface;
        this.baseSchedulerProvider = baseSchedulerProvider;
        this.compositeDisposable = new CompositeDisposable();
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
        if (!getStationId().equals("-1"))
            compositeDisposable.add(
                    dataSourceInterface.getSensors(this.stationId).observeOn(baseSchedulerProvider.getUIScheduler())
                            .startWith(ListViewModelSensors.loading()
                            )
                            .onErrorReturn(
                                    new Function<Throwable, ListViewModelSensors>() {
                                        @Override
                                        public ListViewModelSensors apply(Throwable throwable) throws Exception {
                                            return ListViewModelSensors.error(throwable.getMessage());
                                        }
                                    }
                            )
                            .subscribeWith(new DisposableSubscriber<ListViewModelSensors>() {
                                @Override
                                public void onNext(ListViewModelSensors uiListViewModelSensors) {
                                    if (uiListViewModelSensors.isHasError()) {
                                        // handle this
                                    } else if (uiListViewModelSensors.isLoading()) {
                                        // handle that
                                    } else {
                                        //
                                        //view.showSensorsList((ArrayList<SensorDataModel>) uiListViewModelSensors.getSensorDataModels());
                                        loadSensorsData((ArrayList<SensorDataModel>) uiListViewModelSensors.getSensorDataModels());
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
    public void startSensorsDataActivity(String sensorId) {
        view.showStartSensorsDataActivity(sensorId);
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    @Override
    public void loadSensorData(final SensorDataModel sensorDataModel) {

        compositeDisposable.add(dataSourceInterface.getSensorsData(sensorDataModel.getStationId()).observeOn(baseSchedulerProvider.getUIScheduler())
                .startWith(ViewModelSensorsData.loading())
                .onErrorReturn(
                        new Function<Throwable, ViewModelSensorsData>() {
                            @Override
                            public ViewModelSensorsData apply(Throwable throwable) throws Exception {
                                return ViewModelSensorsData.error(throwable.getMessage());
                            }
                        })
                .subscribeWith(new DisposableSubscriber<ViewModelSensorsData>() {
                    @Override
                    public void onNext(ViewModelSensorsData uiViewModelSensorsData) {
                        if (uiViewModelSensorsData.isHasError()) {

                        } else if (uiViewModelSensorsData.isLoading()) {

                        } else {
                            sensorDataModel.setSensorsDataDataModel(uiViewModelSensorsData.getSensorsDataDataModel());
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
    public void loadSensorsData(ArrayList<SensorDataModel> sensorDataModels) {

        for (SensorDataModel sensorDataModel : sensorDataModels) {
            loadSensorData(sensorDataModel);
            Log.e("lista", sensorDataModel.getSensorsDataDataModel().getValues().toString());
        }
        view.showSensorsList(sensorDataModels);

    }

}
