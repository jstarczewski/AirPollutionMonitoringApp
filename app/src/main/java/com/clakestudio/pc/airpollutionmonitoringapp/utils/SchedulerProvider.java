package com.clakestudio.pc.airpollutionmonitoringapp.utils;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Jan on 9/17/2018.
 */


public class SchedulerProvider implements BaseSchedulerProvider {
    @Override
    public Scheduler getIOScheduler() {
        return Schedulers.io();
    }

    @Override
    public Scheduler getComputerScheduler() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler getUIScheduler() {
        return Schedulers.trampoline();
    }

}

