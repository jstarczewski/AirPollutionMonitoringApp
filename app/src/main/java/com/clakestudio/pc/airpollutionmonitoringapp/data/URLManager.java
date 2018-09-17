package com.clakestudio.pc.airpollutionmonitoringapp.data;

/**
 * Created by Jan on 9/17/2018.
 */

public class URLManager {

    public static final String API_HOST = "http://api.gios.gov.pl";
    public static final String STATIONS = "/pjp-api/rest/station/findAll";
    public static final String SENSORS = "/pjp-api/rest/station/sensors/{stationId}";
    public static final String DATA_FROM_SENSOR = "/pjp-api/rest/data/getData/{sensorId}";
    public static final String AIR_POLLUTION_LEVEL = "/pjp-api/rest/aqindex/getIndex/{stationId}";

}
