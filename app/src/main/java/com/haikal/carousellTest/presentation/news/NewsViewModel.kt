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


class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    fun getNews(callback: (RequestState<List<News>>) -> Unit) {
        callback(RequestState.Loading)
        viewModelScope.launch {
            when (val result = newsRepository.getNews()) {
                is ProcessState.Success -> {
                    val newsList = result.result.map { it.toNews() }  // Convert NewsResponse to News
                    callback(RequestState.Success(newsList))
                }
                is ProcessState.Failed -> {
                    callback(RequestState.Failed(result.error))  // Adjust error message as needed
                }

               is ProcessState.Loading -> {
                    callback(RequestState.Loading)
                }
            }
        }
    }
}