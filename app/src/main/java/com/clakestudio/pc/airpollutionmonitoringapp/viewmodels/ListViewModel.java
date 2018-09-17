package com.clakestudio.pc.airpollutionmonitoringapp.viewmodels;

import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.StationDataModel;

import java.util.List;

/**
 * Created by Jan on 9/17/2018.
 */

public final class ListViewModel {

    final boolean hasError;
    final boolean isLoading;
    final String errorMessage;
    private List<StationDataModel> stationDataModels;

    public ListViewModel(boolean hasError, boolean isLoading, String errorMessage, List<StationDataModel> stationDataModels) {
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

    public List<StationDataModel> getStationDataModels() {
        return stationDataModels;
    }

    public static ListViewModel loading() {
        return new ListViewModel(false, true, null, null);
    }

    public static ListViewModel success(List<StationDataModel> stationDataModels) {
        return new ListViewModel(false, false, null, stationDataModels);
    }

    public static ListViewModel error(String errorMessage) {
        return new ListViewModel(true, false, errorMessage, null);
    }
}
