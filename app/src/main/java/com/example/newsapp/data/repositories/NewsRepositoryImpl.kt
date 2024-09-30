package com.example.newsapp.data.repositories

import com.example.newsapp.data.remote.NewsAPIService
import com.example.newsapp.domain.models.ArticlesResponse
import com.example.newsapp.domain.repositories.news_repository.NewsRepository
import retrofit2.Call

class NewsRepositoryImpl(
    val newsAPIService: NewsAPIService
) : NewsRepository{
    override fun getArticlesBySource(source: String): Call<ArticlesResponse> {
        return newsAPIService.getArticlesBySources(source)
    }
}