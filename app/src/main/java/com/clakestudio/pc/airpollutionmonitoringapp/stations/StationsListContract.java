package com.clakestudio.pc.airpollutionmonitoringapp.stations;

import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.StationDataModel;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BasePresenter;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BaseView;

import java.util.ArrayList;

/**
 * Created by Jan on 9/17/2018.
 */

public class StationsListContract {

    public interface View extends BaseView<Presenter> {

        void showStationList(ArrayList<StationDataModel> stationDataModels);

        void showStartSensorsListActivity();

    }

    public interface Presenter extends BasePresenter<View> {

        void loadStationList();

        void startSensorsListActivity();

    }


}
