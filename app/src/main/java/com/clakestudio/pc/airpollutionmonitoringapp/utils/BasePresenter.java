package com.clakestudio.pc.airpollutionmonitoringapp.utils;

/**
 * Created by Jan on 9/17/2018.
 */

public interface BasePresenter<T> {

    void takeView(T view);

    void dropView();

    void start();

    void stop();

}
