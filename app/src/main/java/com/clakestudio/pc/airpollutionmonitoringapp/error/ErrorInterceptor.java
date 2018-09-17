package com.clakestudio.pc.airpollutionmonitoringapp.error;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Jan on 9/17/2018.
 */


public class ErrorInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        if (!response.isSuccessful()) {
            throw new AirPollutionError(
                    response.code(),
                    response.message()
            );
        }
            return response;
    }
}
