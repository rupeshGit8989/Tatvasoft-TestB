package com.tatvasoft.test.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.tatvasoft.test.data.remote.ApiService
import com.tatvasoft.test.data.repository.UserDataSource
import com.tatvasoft.test.model.UserItem
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class UserDataSourceFactory @Inject constructor(
    private val scope: CoroutineScope,
    private val apiService: ApiService
) : DataSource.Factory<Int, UserItem>() {

    private val liveData = MutableLiveData<UserDataSource>()

    override fun create(): DataSource<Int, UserItem> {
        val source = UserDataSource(apiService, scope)
        liveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 5

        fun pagedListConfig() =
            PagedList.Config.Builder().setInitialLoadSizeHint(PAGE_SIZE).setPageSize(PAGE_SIZE)
                .setEnablePlaceholders(true).build()
    }
}