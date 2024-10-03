package com.example.newsapp.data.remote

import com.example.newsapp.domain.models.ArticlesResponse
import com.example.newsapp.domain.models.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {
    @GET("top-headlines/sources")
    fun getSources() : Call<SourcesResponse>

    @GET("everything")
    fun getArticles(
        @Query("q") query : String,
        @Query("sources") sources : String
    ) : Call<ArticlesResponse>

    @GET("everything")
    fun getArticlesByQuery(
        @Query("q") query : String
    ) : Call<ArticlesResponse>

}