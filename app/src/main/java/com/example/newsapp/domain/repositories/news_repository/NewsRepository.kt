package com.example.newsapp.domain.repositories.news_repository

import com.example.newsapp.domain.models.ArticlesResponse
import retrofit2.Call

interface NewsRepository {
    fun getArticles(query: String, source: String) : Call<ArticlesResponse>

    fun getArticlesByQuery(query: String) : Call<ArticlesResponse>
}