package com.clakestudio.pc.airpollutionmonitoringapp.data;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Jan on 9/17/2018.
 */

public class AirPollutionRestAdapter {

    public interface AirPollutionService {

        @GET(URLManager.STATIONS)
        Flowable<List<StationDataModel>> getStations();

        @GET(URLManager.SENSORS)
        Flowable<List<SensorDataModel>> getSensors(
                @Path("stationId")
                String stationId
                );

        /**
         *  Other methods gonna be implemented when those two above will work
         *
         *  @GET(URLManager.DATA_FROM_SENSOR)
         *  Flowable<SensorValueDataModel>
         *
         * */

    }

}
