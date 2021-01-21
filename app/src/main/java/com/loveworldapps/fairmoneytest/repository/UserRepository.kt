package com.loveworldapps.fairmoneytest.repository

import com.iconic_dev.telleriumfarms.db.UsersDatabase
import com.loveworldapps.fairmoneytest.api.FairMoneyApi
import com.loveworldapps.fairmoneytest.api.models.User
import com.loveworldapps.fairmoneytest.api.models.UserResponse
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by manuelchris-ogar on 18/01/2021.
 */
class UserRepository(private val fairMoneyApi: FairMoneyApi,private val db:UsersDatabase) {

    fun loadUsers(limit: Int, page:Int =0): Observable<UserResponse?>? {
        return fairMoneyApi.fetchUsers(limit, page)
    }

     fun addAllUsers(users: List<User>): Single<List<Long>> {
            return db.usersDao.addAllUsers(users)

    }

    fun addUser(user:User){
        db.usersDao.insert(user)
    }


    fun getUserNumber():Int{
        return db.usersDao.getCount();
    }

    fun getAllUsers() = db.usersDao.getAll()

}