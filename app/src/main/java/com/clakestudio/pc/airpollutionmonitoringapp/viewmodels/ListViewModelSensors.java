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
    private List<SensorDataModel> sensorDataModels;


    public ListViewModelSensors(boolean hasError, boolean isLoading, String errorMessage, List<SensorDataModel> sensorDataModels) {
        this.hasError = hasError;
        this.isLoading = isLoading;
        this.errorMessage = errorMessage;
        this.sensorDataModels = sensorDataModels;
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
        return sensorDataModels;
    }

    public static ListViewModelSensors loading() {
        return new ListViewModelSensors(false, true, null, null);
    }

    public static ListViewModelSensors success(List<SensorDataModel> sensorDataModels) {
        return new ListViewModelSensors(false, false, null, sensorDataModels);
    }

    public static ListViewModelSensors error(String errorMessage) {
        return new ListViewModelSensors(true, false, errorMessage, null);
    }
}
