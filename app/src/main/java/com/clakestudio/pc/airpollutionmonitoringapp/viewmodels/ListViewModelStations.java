package com.clakestudio.pc.airpollutionmonitoringapp.viewmodels;

import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.SensorDataModel;
import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.StationDataModel;

import java.util.List;

/**
 * Created by Jan on 9/17/2018.
 */

public final class ListViewModelStations {

    final boolean hasError;
    final boolean isLoading;
    final String errorMessage;
    private List<StationDataModel> stationDataModels;


    public ListViewModelStations(boolean hasError, boolean isLoading, String errorMessage, List<StationDataModel> stationDataModels) {
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

    public static ListViewModelStations loading() {
        return new ListViewModelStations(false, true, null, null);
    }

    public static ListViewModelStations success(List<StationDataModel> stationDataModels) {
        return new ListViewModelStations(false, false, null, stationDataModels);
    }

    public static ListViewModelStations error(String errorMessage) {
        return new ListViewModelStations(true, false, errorMessage, null);
    }
}
