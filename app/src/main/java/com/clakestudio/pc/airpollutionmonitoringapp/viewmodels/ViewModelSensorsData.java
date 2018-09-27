package com.clakestudio.pc.airpollutionmonitoringapp.viewmodels;

import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.SensorsDataDataModel;

/**
 * Created by Jan on 9/22/2018.
 */

public class ViewModelSensorsData {

    final boolean hasError;
    final boolean isLoading;
    final String errorMessage;
    private SensorsDataDataModel sensorsDataDataModel;


    public ViewModelSensorsData(boolean hasError, boolean isLoading, String errorMessage, SensorsDataDataModel sensorsDataDataModel) {
        this.hasError = hasError;
        this.isLoading = isLoading;
        this.errorMessage = errorMessage;
        this.sensorsDataDataModel = sensorsDataDataModel;
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

    public SensorsDataDataModel getSensorsDataDataModel() {
        return this.sensorsDataDataModel;
    }

    public static ViewModelSensorsData loading() {
        return new ViewModelSensorsData(false, true, null, null);
    }

    public static ViewModelSensorsData success(SensorsDataDataModel sensorsDataDataModels) {
        return new ViewModelSensorsData(false, false, null, sensorsDataDataModels);
    }

    public static ViewModelSensorsData error(String errorMessage) {
        return new ViewModelSensorsData(true, false, errorMessage, null);
    }

}
