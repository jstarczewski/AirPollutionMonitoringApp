package com.clakestudio.pc.airpollutionmonitoringapp.datamodels;

import java.util.List;

/**
 * Created by Jan on 9/22/2018.
 */

public class SensorsDataDataModel {

    final String key;
//    final List<Value> values;

    public SensorsDataDataModel(String key){//, List<Value> values) {

        this.key = key;
 //       this.values = values;
    }

    public String getKey() {
        return key;
    }

    //public List<Value> getValues() {
      //  return values;
   // }

    public static class Value {

        final String date;
        final String value;

        public Value(String date, String value) {
            this.date = date;
            this.value = value;
        }

        public String getDate() {
            return date;
        }

        public String getValue() {
            return value;
        }
    }


}
