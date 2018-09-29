package com.clakestudio.pc.airpollutionmonitoringapp.utils;

import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.SensorsDataDataModel;

import java.util.ArrayList;

/**
 * Created by Jan on 9/23/2018.
 */

public abstract class SensorsDataFormatter {

    public static SensorsDataDataModel formatNulls(SensorsDataDataModel sensorsDataDataModelBeforeFormatting) {

        ArrayList<SensorsDataDataModel.Value> values = new ArrayList<>();

        for (SensorsDataDataModel.Value sensorsDataDataModelValue : sensorsDataDataModelBeforeFormatting.getValues()) {
            if (sensorsDataDataModelValue.getValue()==null || sensorsDataDataModelValue.getValue().equals("null")) {
                values.add(new SensorsDataDataModel.Value(sensorsDataDataModelValue.getData(), "not measured"));
            }
            else {
                values.add(new SensorsDataDataModel.Value(sensorsDataDataModelValue.getData(), sensorsDataDataModelValue.getValue()));
            }
        }

        return new SensorsDataDataModel(sensorsDataDataModelBeforeFormatting.getKey(), values);
    }
    public static String formatToString(String toStringBeforeFormatting) {

        toStringBeforeFormatting =  toStringBeforeFormatting.replace(',', ' ');
        toStringBeforeFormatting = toStringBeforeFormatting.substring(1, toStringBeforeFormatting.length()-1);
        return toStringBeforeFormatting;

    }

}
