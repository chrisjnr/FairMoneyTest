package com.loveworldapps.data

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.loveworldapps.data.local.dao.UsersDao
import com.loveworldapps.data.local.db.UsersDatabase
import com.loveworldapps.data.mapper.entity.UserEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.IsEqual.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DbTest {
    private lateinit var userDao: UsersDao
    private lateinit var db: UsersDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getContext()
        db = Room.inMemoryDatabaseBuilder(
                context, UsersDatabase::class.java).build()
        userDao = db.usersDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadUser()= runBlocking {
        val user = UserEntity("", "", "", "", "Mannie", "")
        userDao.insert(user)
        val byName = userDao.searchForUser("Mannie")
        assertThat(byName!![0], equalTo(user))
    }

}