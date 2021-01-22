package com.loveworldapps.domain.model.repository.base

import androidx.paging.DataSource
import com.loveworldapps.domain.model.User
import com.loveworldapps.domain.model.UserResponse
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by manuelchris-ogar on 21/01/2021.
 */
interface UserRepository {

    fun loadUsers(limit: Int, page:Int =0): Observable<UserResponse?>?

    fun addAllUsers(users: List<User>): Single<List<Long>>

    fun addUser(user:User)

    fun getUserNumber():Int

    fun getAllUsers(): DataSource.Factory<Int, User>

}