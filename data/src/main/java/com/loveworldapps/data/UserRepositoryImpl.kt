package com.loveworldapps.data

import androidx.paging.DataSource
import com.loveworldapps.data.di.provideMapper
import com.loveworldapps.data.local.db.UsersDatabase
import com.loveworldapps.data.mapper.UserEntityMapper
import com.loveworldapps.data.remote.FairMoneyApi
import com.loveworldapps.domain.model.User
import com.loveworldapps.domain.model.UserResponse
import com.loveworldapps.domain.model.repository.base.UserRepository
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by manuelchris-ogar on 22/01/2021.
 */
class UserRepositoryImpl(private val fairMoneyApi: FairMoneyApi, private val db: UsersDatabase, val userEntityMapper: UserEntityMapper) : UserRepository {


    override fun loadUsers(limit: Int, page:Int): Observable<UserResponse?>? =  fairMoneyApi.fetchUsers(limit, page = page)



    override fun addAllUsers(users: List<User>) = db.usersDao.addAllUsers(userEntityMapper.mapFromDomainList(users))

    override fun addUser(user:User) = db.usersDao.insert(userEntityMapper.mapToEntity(user))


    override fun getUserNumber():Int{
        return db.usersDao.getCount();
    }

    override fun getAllUsers(): DataSource.Factory<Int, User> = db.usersDao.getAll().map { userEntityMapper.mapFromEntity(it) }


}