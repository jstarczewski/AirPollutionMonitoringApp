package com.clakestudio.pc.airpollutionmonitoringapp.data;

/**
 * Created by Jan on 9/17/2018.
 */

public class URLManager {

    public static final String API_HOST = "http://api.gios.gov.pl/pjp-api/rest";
    public static final String STATIONS = "/station/findAll";
    public static final String SENSORS = "/station/sensors/{stationId}";
    public static final String DATA_FROM_SENSOR = "/data/getData/{sensorId}";
    public static final String AIR_POLLUTION_LEVEL = "/aqindex/getIndex/{stationId}";

}
