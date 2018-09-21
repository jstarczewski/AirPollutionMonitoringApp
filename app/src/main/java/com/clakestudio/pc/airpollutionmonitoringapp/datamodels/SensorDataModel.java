package com.clakestudio.pc.airpollutionmonitoringapp.datamodels;

import java.util.List;

/**
 * Created by Jan on 9/17/2018.
 */

public class SensorDataModel {

    private final String id;
    private final String stationId;
    private final List<Param> params;

    public SensorDataModel(String id, String stationId, List<Param> params) {
        this.id = id;
        this.stationId = stationId;
        this.params = params;
    }

    public String getId() {
        return id;
    }

    public String getStationId() {
        return stationId;
    }

    public List<Param> getParams() {
        return params;
    }

    static class Param {

        private final String paramName;
        private final String paramFormula;
        private final String paramCode;
        private final String idParam;

        public Param(String paramName, String paramFormula, String paramCode, String idParam) {
            this.paramName = paramName;
            this.paramFormula = paramFormula;
            this.paramCode = paramCode;
            this.idParam = idParam;
        }

        public String getParamName() {
            return paramName;
        }

        public String getParamFormula() {
            return paramFormula;
        }

        public String getParamCode() {
            return paramCode;
        }

        public String getIdParam() {
            return idParam;
        }
    }
    @Override
    public String toString() {
        return this.id  + " " + this.params.toString();
    }


}
