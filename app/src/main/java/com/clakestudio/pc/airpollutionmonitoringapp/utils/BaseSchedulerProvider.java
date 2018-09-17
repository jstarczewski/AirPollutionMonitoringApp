package com.clakestudio.pc.airpollutionmonitoringapp.utils;

import io.reactivex.Scheduler;

/**
 * Created by Jan on 9/17/2018.
 */

public interface BaseSchedulerProvider {

    Scheduler getIOScheduler();

    Scheduler getComputerScheduler();

    Scheduler getUIScheduler();

}
