package com.clakestudio.pc.airpollutionmonitoringapp.data;

import com.clakestudio.pc.airpollutionmonitoringapp.viewmodels.ListViewModel;

import io.reactivex.Flowable;

/**
 * Created by Jan on 9/17/2018.
 */

public interface AirPollutionDataSourceInterface {

    Flowable<ListViewModel> getStations();


}
