package com.paem.core.data.remote


import com.paem.core.data.remote.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("carousell_news.json")
    suspend fun getNews(): Response<List<NewsResponse>>


}