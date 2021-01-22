package com.loveworldapps.domain.model

import com.loveworldapps.domain.model.base.Model


/**
 * Created by manuelchris-ogar on 21/01/2021.
 */

data class User (
    val id : String,
    val email : String,
    val title : String,
    val picture : String,
    val firstName : String,
    val lastName : String
):Model()