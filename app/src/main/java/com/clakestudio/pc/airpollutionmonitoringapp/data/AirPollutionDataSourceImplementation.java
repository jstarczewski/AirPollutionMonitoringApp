package com.clakestudio.pc.airpollutionmonitoringapp.data;

import com.clakestudio.pc.airpollutionmonitoringapp.datamodels.StationDataModel;
import com.clakestudio.pc.airpollutionmonitoringapp.error.EmptyDatasetException;
import com.clakestudio.pc.airpollutionmonitoringapp.viewmodels.ListViewModel;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
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
    public Flowable<ListViewModel> getStations() {

        return
                airPollutionRestAdapter.getStations()
                .flatMap(new Function<List<StationDataModel>,
                        Publisher<ListViewModel>>() {
                    @Override
                    public Publisher<ListViewModel>
                    apply(List<StationDataModel> stationDataModelsResponse) throws Exception {

                        List<StationDataModel> stationDataModels = new ArrayList<>();
                        if (stationDataModelsResponse.size()==0)
                            throw new EmptyDatasetException();

                        for (StationDataModel stationDataModelResponse : stationDataModelsResponse) {
                            stationDataModels.add(new StationDataModel(stationDataModelResponse.getId(), stationDataModelResponse.getStationName(), stationDataModelResponse.getCity()));
                        }

                        return Flowable.just(ListViewModel.success(stationDataModels));
                    }
                }).subscribeOn(Schedulers.io());

    }
}
