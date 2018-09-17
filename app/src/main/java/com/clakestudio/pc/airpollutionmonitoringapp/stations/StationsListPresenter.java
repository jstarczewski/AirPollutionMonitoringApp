package com.clakestudio.pc.airpollutionmonitoringapp.stations;

import com.clakestudio.pc.airpollutionmonitoringapp.data.AirPollutionDataSourceInterface;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BasePresenter;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BaseSchedulerProvider;
import com.clakestudio.pc.airpollutionmonitoringapp.viewmodels.ListViewModel;

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
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loadStationList() {
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
                        }
                        else if (uiListViewModel.isLoading()) {
                            //view.showLoadingIndicator();
                        }
                        else {
                            //view.setUpAdapterAndView(uiListViewModel.getStationsList();
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
