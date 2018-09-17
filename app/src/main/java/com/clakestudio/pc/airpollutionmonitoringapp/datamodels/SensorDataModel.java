package com.clakestudio.pc.airpollutionmonitoringapp.datamodels;

/**
 * Created by Jan on 9/17/2018.
 */

public class SensorDataModel {

    private final String id;
    private final String stationId;
    private final Param param;

    public SensorDataModel(String id, String stationId, Param param) {
        this.id = id;
        this.stationId = stationId;
        this.param = param;
    }

    public String getId() {
        return id;
    }

    public String getStationId() {
        return stationId;
    }

    public Param getParam() {
        return param;
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

}
