package com.clakestudio.pc.airpollutionmonitoringapp.error;

import java.io.IOException;

/**
 * Created by Jan on 9/17/2018.
 */

public class EmptyDatasetException extends IOException {

    private final String message;

    public EmptyDatasetException() {
        this.message = "No response";
    }
    @Override
    public String getMessage() {
        return message;
    }
}
