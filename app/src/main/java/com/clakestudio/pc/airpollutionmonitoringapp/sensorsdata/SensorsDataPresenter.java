package com.clakestudio.pc.airpollutionmonitoringapp.sensorsdata;

import com.clakestudio.pc.airpollutionmonitoringapp.data.AirPollutionDataSourceInterface;
import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.SensorsDataDataModel;
import com.clakestudio.pc.airpollutionmonitoringapp.di.ActivityScoped;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BaseSchedulerProvider;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.SensorsDataFormatter;
import com.clakestudio.pc.airpollutionmonitoringapp.viewmodels.ViewModelSensorsData;

import java.util.ArrayList;

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
    SensorsDataPresenter(AirPollutionDataSourceInterface airPollutionDataSourceInterface, BaseSchedulerProvider baseSchedulerProvider) {
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
                            ArrayList<SensorsDataDataModel> sensorsDataDataModels = new ArrayList<>();
                            sensorsDataDataModels.add(uiViewModelSensorsData.getSensorsDataDataModel());
                            sensorsDataDataModels.add(SensorsDataFormatter.format(uiViewModelSensorsData.getSensorsDataDataModel()));
                            view.showSensorsData(sensorsDataDataModels);
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
