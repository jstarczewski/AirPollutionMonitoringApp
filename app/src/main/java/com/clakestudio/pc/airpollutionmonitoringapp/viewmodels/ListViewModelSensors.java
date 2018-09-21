package com.clakestudio.pc.airpollutionmonitoringapp.viewmodels;

import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.SensorDataModel;

import java.util.List;

/**
 * Created by Jan on 9/21/2018.
 */

public class ListViewModelSensors {

    final boolean hasError;
    final boolean isLoading;
    final String errorMessage;
    private List<SensorDataModel> stationDataModels;
    private List<SensorDataModel> sensorDataModels;


    public ListViewModelSensors(boolean hasError, boolean isLoading, String errorMessage, List<SensorDataModel> stationDataModels) {
        this.hasError = hasError;
        this.isLoading = isLoading;
        this.errorMessage = errorMessage;
        this.stationDataModels = stationDataModels;
    }

    public boolean isHasError() {
        return hasError;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<SensorDataModel> getSensorDataModels() {
        return stationDataModels;
    }

    public static ListViewModelSensors loading() {
        return new ListViewModelSensors(false, true, null, null);
    }

    public static ListViewModelSensors success(List<SensorDataModel> stationDataModels) {
        return new ListViewModelSensors(false, false, null, stationDataModels);
    }

    public static ListViewModelSensors error(String errorMessage) {
        return new ListViewModelSensors(true, false, errorMessage, null);
    }
}
