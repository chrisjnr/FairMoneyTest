package com.loveworldapps.fairmoneytest.api.config;

import androidx.annotation.NonNull;

import com.loveworldapps.fairmoneytest.BuildConfig;

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
        Request originalRequest = chain.request();

        HttpUrl url = originalRequest.url().newBuilder()
                .build();

        Headers.Builder headerBuilder = originalRequest.headers().newBuilder();

            headerBuilder.add("app-id", BuildConfig.api_key);

        Request authorizationRequest = originalRequest.newBuilder()
                .url(url)
                .headers(headerBuilder.build())
                .build();

        return chain.proceed(authorizationRequest);
    }


}
