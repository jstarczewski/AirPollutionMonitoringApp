package com.clakestudio.pc.airpollutionmonitoringapp.data;

import android.util.Log;
import android.widget.Toast;

import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.SensorDataModel;
import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.StationDataModel;
import com.clakestudio.pc.airpollutionmonitoringapp.error.EmptyDatasetException;
import com.clakestudio.pc.airpollutionmonitoringapp.viewmodels.ListViewModelSensors;
import com.clakestudio.pc.airpollutionmonitoringapp.viewmodels.ListViewModelStations;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Jan on 9/17/2018.
 */

public class AirPollutionDataSourceImplementation implements AirPollutionDataSourceInterface {

    private AirPollutionRestAdapter airPollutionRestAdapter;

    @Inject
    public AirPollutionDataSourceImplementation(AirPollutionRestAdapter airPollutionRestAdapter) {
        this.airPollutionRestAdapter = airPollutionRestAdapter;
    }


    @Override
    public Flowable<ListViewModelStations> getStations() {

        return
                airPollutionRestAdapter.getStations()
                        .flatMap(new Function<List<StationDataModel>,
                                Publisher<ListViewModelStations>>() {
                            @Override
                            public Publisher<ListViewModelStations>
                            apply(List<StationDataModel> stationDataModelsResponse) throws Exception {

                                List<StationDataModel> stationDataModels = new ArrayList<>();
                                if (stationDataModelsResponse.size() == 0)
                                    throw new EmptyDatasetException();

                                for (StationDataModel stationDataModelResponse : stationDataModelsResponse) {
                                    stationDataModels.add(new StationDataModel(stationDataModelResponse.getId(), stationDataModelResponse.getStationName(), stationDataModelResponse.getCity()));
                                }

                                return Flowable.just(ListViewModelStations.success(stationDataModels));
                            }
                        }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Flowable<ListViewModelSensors> getSensors(String stationId) {
        return
                airPollutionRestAdapter.getSensors(stationId)
                        .flatMap(new Function<List<SensorDataModel>, Publisher<ListViewModelSensors>>() {
                            @Override
                            public Publisher<ListViewModelSensors>
                            apply(List<SensorDataModel> sensorDataModelsResponse) throws Exception {

                                List<SensorDataModel> sensorDataModels = new ArrayList<>();
                                if (sensorDataModelsResponse.size() == 0)
                                    throw new EmptyDatasetException();

                                for (SensorDataModel sensorDataModelResponse : sensorDataModelsResponse) {
                        //            Log.e("param", " -> " + sensorDataModelResponse.toString());
                                 //   System.out.println(sensorDataModelResponse.toString());
                                    sensorDataModels.add(new SensorDataModel(sensorDataModelResponse.getId(), sensorDataModelResponse.getStationId()));
                                }

                                return Flowable.just(ListViewModelSensors.success(sensorDataModels));
                            }
                        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
