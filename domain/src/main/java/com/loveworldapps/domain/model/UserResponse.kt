package com.loveworldapps.domain.model

import com.loveworldapps.domain.model.base.BaseResponse

/**
 * Created by manuelchris-ogar on 21/01/2021.
 */
data class UserResponse  (
    val data : List<User>,
    val total : Int,
    val page : Int,
    val limit : Int,
    val offset : Int
): BaseResponse()