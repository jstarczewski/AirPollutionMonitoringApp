package com.clakestudio.pc.airpollutionmonitoringapp.stations;

import com.clakestudio.pc.airpollutionmonitoringapp.utils.BasePresenter;
import com.clakestudio.pc.airpollutionmonitoringapp.utils.BaseView;

/**
 * Created by Jan on 9/17/2018.
 */

public class StationsListContract {

    interface View extends BaseView<Presenter> {

        void showStationList();

        void showStartSensorsListActivity();

    }

    interface Presenter extends BasePresenter {

        void loadStationList();

        void startSensorsListActivty();

    }


}
