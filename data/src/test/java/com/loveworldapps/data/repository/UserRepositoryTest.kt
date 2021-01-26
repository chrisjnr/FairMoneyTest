package com.loveworldapps.data.repository

import androidx.room.Room
import com.google.common.truth.Truth.assertThat
import com.loveworldapps.data.DummyData
import com.loveworldapps.data.UserRepositoryImpl
import com.loveworldapps.data.di_test.configureTestAppComponent
import com.loveworldapps.data.kointest.BaseKoinTest
import com.loveworldapps.data.local.db.UsersDatabase
import com.loveworldapps.data.mapper.UserEntityMapper
import com.loveworldapps.data.remote.FairMoneyApi
import com.loveworldapps.domain.model.UserResponse
import io.reactivex.observers.TestObserver
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import java.net.HttpURLConnection


/**
 * Created by manuelchris-ogar on 25/01/2021.
 */

@RunWith(RobolectricTestRunner::class)
class UserRepositoryTest: BaseKoinTest(){


    private lateinit var mRepo: UserRepositoryImpl

    //Inject api service created with koin
    val mAPIService : FairMoneyApi by inject()

    val db : UsersDatabase by inject()
    lateinit var testDb : UsersDatabase
    lateinit var userEntityMapper:UserEntityMapper

    @Before
    fun start(){
        super.setUp()

        val context = RuntimeEnvironment.application
        testDb = Room.inMemoryDatabaseBuilder(context, UsersDatabase::class.java)
                .allowMainThreadQueries()
                .build()

        startKoin{
            androidContext(RuntimeEnvironment.application)
            modules(configureTestAppComponent(getMockWebServerUrl()))


        }

        userEntityMapper = UserEntityMapper()
        mRepo = UserRepositoryImpl(userEntityMapper = userEntityMapper, fairMoneyApi = mAPIService, db = testDb)

    }

    /**
     * This method will use the mock data
     */
    @Test
    fun `test that repo retrieves expected data`() {
        val ts = TestObserver.create<UserResponse>()
        mockNetworkResponseWithFileContent("success_user_resp_list.json", HttpURLConnection.HTTP_OK)
        mRepo = UserRepositoryImpl(userEntityMapper = userEntityMapper, fairMoneyApi = mAPIService, db = db)

        mRepo.loadUsers(10)!!.subscribe(ts)

        ts.assertNoErrors()

        ts.assertValue { value-> value.limit==10 }
    }


    @Test
    fun `test searchForUser method`(){
        val user = DummyData.DummyUser
        mRepo.addUser(user)
        val byName = mRepo.searchForUser("Emilie")
        assertThat(byName[0].lastName).isEqualTo("Lambert")
        assertThat(byName[0]).isEqualTo(user)
    }


    @Test
    fun `check that user was added to db`(){
        val user = DummyData.DummyUser
        mRepo.addUser(user)
        assertEquals(mRepo.getUserNumber(), 1)
    }


    @Test
    fun `check that searchUser returns empty list when no character is found`() {
        val byName = mRepo.searchForUser("Mannie")
        assertThat(byName).isEmpty()
    }





}