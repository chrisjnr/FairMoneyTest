package com.loveworldapps.data.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.loveworldapps.data.mapper.entity.UserEntity
import com.loveworldapps.domain.model.User
import io.reactivex.Single

/**
 * Created by manuelchris-ogar on 21/01/2021.
 */

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: UserEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(user: UserEntity)

    @Query("Delete from user_table")
    suspend fun deleteAll()

    @Query("Select * from user_table")
    fun getAll(): DataSource.Factory<Int, UserEntity>

    @Query("Select * from user_table where firstName like :query")
    fun searchForUser(query: String?): List<UserEntity?>?


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addAllUsers(users:List<UserEntity>): Single<List<Long>>

    @Query("SELECT * FROM user_table WHERE id= :id")
    fun getUser(id: Long) : LiveData<UserEntity>

    @Query("Select COUNT(id) FROM user_table")
    fun getCount():Int
}