package com.clakestudio.pc.airpollutionmonitoringapp.datamodels;

/**
 * Created by Jan on 9/17/2018.
 */

public class StationDataModel {

    private String id;
    private String stationName;
    private City city;

    public StationDataModel(String id, String stationName, City city) {
        this.id = id;
        this.stationName = stationName;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public String getStationName() {
        return stationName;
    }

    public City getCity() {
        return city;
    }

    static class City {

        private final String name;


        private final String id;

        public City(String id, String name) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }
    }

    public String getCityId() {
        return city.getId();
    }

    public String getCityName() {
        return city.getName();
    }

    @Override
    public String toString() {
        return stationName + " ->> " + city.getName();
    }
}
