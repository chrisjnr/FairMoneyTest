package com.loveworldapps.data.mapper.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by manuelchris-ogar on 21/01/2021.
 */
@Entity(tableName = "user_table")
data class UserEntity(

    @NonNull
    @PrimaryKey
    val id: String,
    val email: String,
    val title: String,
    val picture: String,
    val firstName: String,
    val lastName: String
)