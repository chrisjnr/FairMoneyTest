package com.loveworldapps.fairmoneytest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.loveworldapps.fairmoneytest.api.dataSource.*
import com.loveworldapps.fairmoneytest.api.models.User
import com.loveworldapps.fairmoneytest.repository.UserRepository
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by manuelchris-ogar on 18/01/2021.
 */
class UserViewModel(private val userRepository: UserRepository):ViewModel() {

    lateinit var storedUsers : LiveData<PagedList<User>>

    val selectedUser: MutableLiveData<User> = MutableLiveData()

    var progressLoadStatus: LiveData<String> = MutableLiveData()
        private set
    private val compositeDisposable = CompositeDisposable()


//    initialize paging
    private fun initializePaging() {
    val userBoundaryCallback = UserBoundaryCallback(
            userRepository
    )

    val pagedListConfig = PagedList.Config.Builder()
            .setPrefetchDistance(50)
            .setPageSize(10).build()

    storedUsers = LivePagedListBuilder(
            userRepository.getAllUsers(),
            pagedListConfig
    ).setBoundaryCallback(userBoundaryCallback).build()


            progressLoadStatus = userBoundaryCallback.progressLiveStatus



    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


    init {
        initializePaging()
    }
}