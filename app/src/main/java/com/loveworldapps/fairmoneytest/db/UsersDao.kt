package com.loveworldapps.fairmoneytest.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.loveworldapps.fairmoneytest.api.models.User
import io.reactivex.Single

/**
 * Created by manuelchris-ogar on 19/01/2021.
 */
@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(farmer: User)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(farmer: User)

    @Query("Delete from user_table")
    suspend fun deleteAll()

    @Query("Select * from user_table")
    fun getAll(): DataSource.Factory<Int, User>

    @Query("Select * from user_table where firstName like :query")
    fun searchForUser(query: String?): List<User?>?


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addAllUsers(users:List<User>): Single<List<Long>>

    @Query("SELECT * FROM user_table WHERE id= :id")
    fun getUser(id: Long) : LiveData<User>

    @Query("Select COUNT(id) FROM user_table")
    fun getCount():Int
}