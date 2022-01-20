package com.tatvasoft.test.data.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.tatvasoft.test.data.remote.ApiService
import com.tatvasoft.test.model.UserItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserDataSource @Inject constructor(private val apiService: ApiService, private val scope: CoroutineScope) : PageKeyedDataSource<Int, UserItem>() {

    private val TAG = "UserDataSource"

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, UserItem>) {
        fetchData(1, params.requestedLoadSize) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, UserItem>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, UserItem>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page - 1)
        }
    }

    private fun fetchData(page: Int, pageSize: Int, callback: (List<UserItem>) -> Unit) {
        scope.launch(getJobErrorHandler()) {
            val response = apiService.fetchUsers(page, pageSize)

            val remoteData = response.body()?.data

            // Check for response validation
            if (response.isSuccessful && remoteData != null) { // Save posts into the persistence storage
                callback(remoteData)
            } else { // Something went wrong!
                postError(response.errorBody().toString())
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.toString())
    }

    private fun postError(message: String) {
        Log.e(TAG, "An error happened: $message")
    }
}