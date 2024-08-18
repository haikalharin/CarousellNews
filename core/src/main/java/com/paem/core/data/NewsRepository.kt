package com.paem.core.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.paem.core.base.BaseRepository
import com.paem.core.data.remote.NetworkState
import com.paem.core.data.remote.ProcessState
import com.paem.core.data.remote.model.NewsResponse

import com.paem.core.data.remote.stateNetworkCall
import com.paem.core.entities.News


class NewsRepository : BaseRepository() {

    suspend fun getNews(): ProcessState<List<NewsResponse>> {
        val response =
            stateNetworkCall { network.getNews() }
        return if (response is NetworkState.Success) {
            ProcessState.Success(response.result)
        } else {
            ProcessState.Failed(response as NetworkState.Failed)
        }
    }


}