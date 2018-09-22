package com.clakestudio.pc.airpollutionmonitoringapp.viewmodels;

import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.SensorsDataDataModel;

import java.util.List;

/**
 * Created by Jan on 9/22/2018.
 */

public class ListViewModelSensorsData {


    final boolean hasError;
    final boolean isLoading;
    final String errorMessage;
    private List<SensorsDataDataModel> sensorsDataDataModels;


    public ListViewModelSensorsData(boolean hasError, boolean isLoading, String errorMessage, List<SensorsDataDataModel> sensorsDataDataModels) {
        this.hasError = hasError;
        this.isLoading = isLoading;
        this.errorMessage = errorMessage;
        this.sensorsDataDataModels = sensorsDataDataModels;
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

    public List<SensorsDataDataModel> getSensorsDataDataModels() {
        return this.sensorsDataDataModels;
    }

    public static ListViewModelSensorsData loading() {
        return new ListViewModelSensorsData(false, true, null, null);
    }

    public static ListViewModelSensorsData success(List<SensorsDataDataModel> sensorsDataDataModels) {
        return new ListViewModelSensorsData(false, false, null, sensorsDataDataModels);
    }

    public static ListViewModelSensorsData error(String errorMessage) {
        return new ListViewModelSensorsData(true, false, errorMessage, null);
    }

}
