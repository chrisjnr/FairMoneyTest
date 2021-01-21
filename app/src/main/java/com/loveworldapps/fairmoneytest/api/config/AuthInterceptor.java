package com.loveworldapps.fairmoneytest.api.config;

import androidx.annotation.NonNull;

import com.loveworldapps.fairmoneytest.api.Session;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by manuelchris-ogar on 18/1/2021.
 */
public class AuthInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NonNull  Chain chain) throws IOException {
// TODO: 18/01/2021 use build config constants for api key
        Request originalRequest = chain.request();

        HttpUrl url = originalRequest.url().newBuilder()
                .build();

        Headers.Builder headerBuilder = originalRequest.headers().newBuilder();

            headerBuilder.add("app-id",  "6005788bb90772d5e25dd9c7");

        Request authorizationRequest = originalRequest.newBuilder()
                .url(url)
                .headers(headerBuilder.build())
                .build();

        return chain.proceed(authorizationRequest);
    }


}
