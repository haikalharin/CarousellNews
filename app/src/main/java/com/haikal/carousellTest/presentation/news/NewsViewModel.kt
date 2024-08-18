package com.haikal.carousellTest.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paem.core.data.NewsRepository
import com.paem.core.data.remote.ProcessState
import com.paem.core.data.remote.RequestState
import com.paem.core.entities.News
import com.paem.core.utils.toNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val repo = NewsRepository()

    fun getNews(
        callback: (state: RequestState<List<News>>) -> Unit
    ) {
        callback(RequestState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            val process = repo.getNews()
            launch(Dispatchers.Main) {
                if (process is ProcessState.Success) {
                    val news = process.result
                    if (news != null) {
                        callback(RequestState.Success(news.map { it.toNews() }))
                    } else {
                        callback(RequestState.Success(emptyList()))
                    }
                } else if (process is ProcessState.Failed) {
                    callback(RequestState.Failed(process.error))
                }
            }
        }
    }

}