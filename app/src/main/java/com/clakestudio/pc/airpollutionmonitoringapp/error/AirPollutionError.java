package com.clakestudio.pc.airpollutionmonitoringapp.error;

import java.io.IOException;

/**
 * Created by Jan on 9/17/2018.
 */

public class AirPollutionError extends IOException {
    private int responseCode;
    private String message;

    public int getResponseCode() {
        return responseCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public AirPollutionError(int responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

}
