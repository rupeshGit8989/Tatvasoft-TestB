package com.tatvasoft.test.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.tatvasoft.test.factory.UserDataSourceFactory
import com.tatvasoft.test.model.UserItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepository @Inject constructor(val context: Context, private val userDataSourceFactory: UserDataSourceFactory) {

    fun observePagedUsers() = getDataFromRemote()

    private fun getDataFromRemote(): LiveData<PagedList<UserItem>> {
        return LivePagedListBuilder(userDataSourceFactory, UserDataSourceFactory.pagedListConfig()).build()
    }
}