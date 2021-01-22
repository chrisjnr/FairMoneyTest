package com.loveworldapps.data.remote.config

import com.loveworldapps.domain.model.Constants
import okhttp3.*

/**
 * Created by manuelchris-ogar on 11/01/2021.
 */
class Responses  {

    companion object{
        val TYPE_JSON = MediaType.parse("application/json")
        val NO_INTERNET = "{\"status\": \"error\"," +
                "\"code\": \"" + "No Internet"+ "\"," +
                "\"message\": \"Please check your internet connection and try again.\"," +
                "\"reason\" : \"No Internet Connection\"}"

        fun noInternet(chain:Interceptor.Chain): Response{


            return Response.Builder()
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_1)
                    .code(Constants.NO_INTERNET)
                    .message("Connection Error")
                    .body(ResponseBody.create(TYPE_JSON, NO_INTERNET))
                    .sentRequestAtMillis(-1L)
                    .receivedResponseAtMillis(System.currentTimeMillis())
                    .build()
        }


    }
}