package com.loveworldapps.fairmoneytest.api

import com.google.gson.JsonObject
import com.loveworldapps.fairmoneytest.BuildConfig
import com.loveworldapps.fairmoneytest.api.config.NetworkResponse
import com.loveworldapps.fairmoneytest.api.models.User
import com.loveworldapps.fairmoneytest.api.models.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by manuelchris-ogar on 18/01/2021.
 */
interface FairMoneyApi {

    @GET("api/user")
    fun fetchUsers(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Observable<UserResponse?>?

}