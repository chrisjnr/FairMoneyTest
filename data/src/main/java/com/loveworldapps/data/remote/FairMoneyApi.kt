package com.loveworldapps.data.remote

import com.loveworldapps.domain.model.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by manuelchris-ogar on 21/01/2021.
 */
interface FairMoneyApi {

    @GET("api/user")
    fun fetchUsers(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Observable<UserResponse?>?

}