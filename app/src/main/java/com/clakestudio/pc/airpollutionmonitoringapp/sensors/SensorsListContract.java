package com.clakestudio.pc.airpollutionmonitoringapp.sensors;

import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.SensorDataModel;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BasePresenter;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BaseView;

import java.util.ArrayList;

/**
 * Created by Jan on 9/19/2018.
 */

public interface SensorsListContract {

    interface Presenter extends BasePresenter<View> {

        void loadSensorsList();

        void startSensorsDataActivity();

    }

    interface View extends BaseView<Presenter> {

        void showSensorsList(ArrayList<SensorDataModel> sensorDataModels);

        void showStartSensorsDataActivity(String sensorId);

    }


}
