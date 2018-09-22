package com.clakestudio.pc.airpollutionmonitoringapp.sensorsdata;

import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.SensorsDataDataModel;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BasePresenter;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BaseView;

import java.util.ArrayList;

/**
 * Created by Jan on 9/22/2018.
 */

public interface SensorsDataContract {


    // No need for View to be parametrized with Presenter

    interface View extends BaseView<Presenter> {

        void showSensorsData(ArrayList<SensorsDataDataModel> sensorsDataDataModels);


    }

    interface Presenter extends BasePresenter<View> {

        void loadSensorsData(String sensorId);

    }

}
