package com.loveworldapps.fairmoneytest.api.dataSource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.loveworldapps.data.UserRepositoryImpl
import com.loveworldapps.domain.model.User
import com.loveworldapps.domain.model.UserResponse
import com.loveworldapps.fairmoneytest.Constants
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by manuelchris-ogar on 20/01/2021.
 */
class UserBoundaryCallback(val repository: UserRepositoryImpl) :
        PagedList.BoundaryCallback<User?>() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val progressLiveStatus = MutableLiveData<String>()


    var sourceIndex = 10;
    var lastIndex = 10;
    var total = 0;
    var page = 0;
    override fun onZeroItemsLoaded() {
        fetchUsers()
    }

    override fun onItemAtFrontLoaded(itemAtFront: User) {
//        fetchUsers()
    }

    override fun onItemAtEndLoaded(itemAtEnd: User) {
        if (sourceIndex<=total)fetchUsers()
    }

    /**
     * Fetches contributions using the MediaWiki API
     */
    private fun fetchUsers() {
        progressLiveStatus.postValue(Constants.LOADING)
        compositeDisposable.add(
                repository.loadUsers(sourceIndex, page)
                        !!.subscribeOn(Schedulers.io())
                        ?.subscribe(
                                { result: UserResponse? ->
                                    progressLiveStatus.postValue(Constants.LOADED)
//                                    lastIndex = sourceIndex
                                    lastIndex += result!!.offset
                                    page++;
                                    total = result.total

                                    if (lastIndex > repository.getUserNumber() || repository.getUserNumber()==0){
                                        saveUsersToDb(result.data)
                                    }

                                },
                                { throwable: Throwable? ->
                                    run {
                                        Log.e("errrr", throwable!!.localizedMessage)
                                        progressLiveStatus.postValue(Constants.CHECK_NETWORK_ERROR)
                                    }
                                }
                        )!!
        )
    }

    private fun saveUsersToDb(users: List<User>) {

        compositeDisposable.add(
                repository.addAllUsers(users)
                        .subscribeOn(Schedulers.io())
                        .subscribe { _: List<Long?>? ->

                        }
        )

    }


}