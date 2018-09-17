package com.clakestudio.pc.airpollutionmonitoringapp.stations;

import com.clakestudio.pc.airpollutionmonitoringapp.utils.BasePresenter;

/**
 * Created by Jan on 9/17/2018.
 */

public class StationsListPresenter implements StationsListContract.Presenter {

    private StationsListContract.View view;

    StationsListPresenter(StationsListContract.View view) {
        this.view = view;

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loadStationList() {

    }

    @Override
    public void startSensorsListActivty() {

    }
}
