package com.loveworldapps.fairmoneytest.viewmodel

import androidx.lifecycle.*
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.loveworldapps.data.di.allRepositoryImplViewModules
import com.loveworldapps.data.di.appModuleImpl
import com.loveworldapps.data.local.db.UsersDatabase
import com.loveworldapps.data.mapper.UserEntityMapper
import com.loveworldapps.data.remote.FairMoneyApi
import com.loveworldapps.domain.model.User
import com.loveworldapps.fairmoneytest.base.BaseTest
import com.loveworldapps.fairmoneytest.ui.UserViewModel
import junit.framework.Assert.assertEquals
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by manuelchris-ogar on 26/01/2021.
 */
@RunWith(RobolectricTestRunner::class)
class UserViewModelTest : BaseTest() {


//    in-app test db
    lateinit var testDb : UsersDatabase

    lateinit var userEntityMapper: UserEntityMapper

    //Inject Dependencies created with koin and Koin Modules
    val userViewModel: UserViewModel by inject()

    @Before
    fun start(){
        super.setUp()

        val context = RuntimeEnvironment.application
        testDb = Room.inMemoryDatabaseBuilder(context, UsersDatabase::class.java)
                .allowMainThreadQueries()
                .build()

        startKoin{
            androidContext(context)
            modules(configureViewModelAppComponent(getMockWebServerUrl()))
        }

        userEntityMapper = UserEntityMapper()

    }


    private fun configureViewModelAppComponent(baseApi: String)
            = listOf(
            allRepositoryImplViewModules,
            appModuleImpl,
            mockWebServerModuleForTest,
            configureNetworkModuleForTest(baseApi),
            configureLocalModuleTest()
            )


    private val mockWebServerModuleForTest = module {

        factory {
            MockWebServer()
        }
    }


    private fun configureNetworkModuleForTest(baseApi: String)
            = module(override = true){
        single {
            Retrofit.Builder()
                .baseUrl(baseApi)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        factory{ get<Retrofit>().create(FairMoneyApi::class.java) }
    }


    private fun configureLocalModuleTest() = module(override = true) {
        single {
            Room.inMemoryDatabaseBuilder(get(), UsersDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        }
        single { testDb }
        single { UserViewModel(get()) }
    }


    private fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
        val observer = OneTimeObserver(handler = onChangeHandler)
        observe(observer, observer)
    }

    class OneTimeObserver<T>(private val handler: (T) -> Unit) : Observer<T>, LifecycleOwner {
        private val lifecycle = LifecycleRegistry(this)
        init {
            lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }

        override fun getLifecycle(): Lifecycle = lifecycle

        override fun onChanged(t: T) {
            handler(t)
            lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        }
    }


    private fun createUser() = User(
        id =  "uQrnqsqyh8FjSXAPc7oA",
        email = "emilie.lambert@example.com",
        title= "mrs",
        picture = "https://randomuser.me/api/portraits/women/93.jpg",
        firstName =  "Emilie",
        lastName = "Lambert"
    )

    @Test
    fun `test that viewModel can get updated user number`(){
        val user = createUser()

        userViewModel.userRepository.addUser(user = user)
        assertEquals(userViewModel.userRepository.getUserNumber(), 1)
    }


    @Test
    fun `test that live data updates once a new user is updated in the db`(){
        val user = createUser()

        userViewModel.userRepository.addUser(user = user)
        userViewModel.storedUsers.observeOnce {
            assertEquals(user, it.first())
        }
    }


    @Test
    fun `test that paging data size is updated once a new user is updated in the db`(){
        val user = createUser()
        userViewModel.userRepository.addUser(user = user)
        userViewModel.storedUsers.observeOnce {
            assertEquals(1, it.size)
        }
    }



}