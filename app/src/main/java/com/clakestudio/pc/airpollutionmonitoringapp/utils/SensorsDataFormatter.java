package com.clakestudio.pc.airpollutionmonitoringapp.utils;

import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.SensorsDataDataModel;

import java.util.ArrayList;

/**
 * Created by Jan on 9/23/2018.
 */

public abstract class SensorsDataFormatter {

    public static SensorsDataDataModel format(SensorsDataDataModel sensorsDataDataModelBeforeFormatting) {

        ArrayList<SensorsDataDataModel.Value> values = new ArrayList<>();
        SensorsDataDataModel sensorsDataDataModelAfterFormatting = new SensorsDataDataModel(sensorsDataDataModelBeforeFormatting.getKey(), values);

        for (SensorsDataDataModel.Value sensorsDataDataModelValue : sensorsDataDataModelBeforeFormatting.getValues()) {
            if (sensorsDataDataModelValue.getValue()!=null && sensorsDataDataModelValue.getValue().equals("null")) {
                values.add(new SensorsDataDataModel.Value(sensorsDataDataModelValue.getDate(), "not measured"));
            }
            else {
                values.add(new SensorsDataDataModel.Value(sensorsDataDataModelValue.getDate(), sensorsDataDataModelValue.getValue()));
            }
        }
        return sensorsDataDataModelAfterFormatting;
    }


}
