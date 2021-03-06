package com.clakestudio.pc.airpollutionmonitoringapp.data;

import com.clakestudio.pc.airpollutionmonitoringapp.viewmodels.ListViewModelSensors;
import com.clakestudio.pc.airpollutionmonitoringapp.viewmodels.ViewModelSensorsData;
import com.clakestudio.pc.airpollutionmonitoringapp.viewmodels.ListViewModelStations;

import io.reactivex.Flowable;

/**
 * Created by Jan on 9/17/2018.
 */

public interface AirPollutionDataSourceInterface {

    Flowable<ListViewModelStations> getStations();

    Flowable<ListViewModelSensors> getSensors(String stationId);

    Flowable<ViewModelSensorsData> getSensorsData(String sensorId);

}
