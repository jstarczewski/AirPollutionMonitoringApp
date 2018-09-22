package com.clakestudio.pc.airpollutionmonitoringapp.data;

import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.SensorDataModel;
import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.SensorsDataDataModel;
import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.StationDataModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Jan on 9/17/2018.
 */

public class AirPollutionRestAdapter {

    private final AirPollutionService airPollution;

    public interface AirPollutionService {

        @GET(URLManager.STATIONS)
        Flowable<List<StationDataModel>> getStations();

        @GET(URLManager.SENSORS)
        Flowable<List<SensorDataModel>> getSensors(
                @Path("stationId")
                        String stationId
        );

        @GET(URLManager.DATA_FROM_SENSOR)
        Flowable<List<SensorsDataDataModel>> getSensorsData(
                @Path("sensorId")
                        String sensorId
        );


        /**
         *  Other methods gonna be implemented when those two above will work
         *
         *  @GET(URLManager.DATA_FROM_SENSOR)
         *  Flowable<SensorValueDataModel>
         *
         * */

    }

    @Inject
    public AirPollutionRestAdapter(Retrofit retrofit) {
        this.airPollution = retrofit.create(AirPollutionService.class);
    }

    public Flowable<List<StationDataModel>> getStations() {
        return airPollution.getStations();
    }

    public Flowable<List<SensorDataModel>> getSensors(final String stationId) {
        return airPollution.getSensors(stationId);
    }

    public Flowable<List<SensorsDataDataModel>> getSensorsData(final String sensorId) {
        return airPollution.getSensorsData(sensorId);
    }
}
