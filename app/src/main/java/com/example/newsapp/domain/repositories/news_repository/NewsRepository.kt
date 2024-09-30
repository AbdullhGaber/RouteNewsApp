package com.example.newsapp.domain.repositories.news_repository

import com.example.newsapp.domain.models.ArticlesResponse
import retrofit2.Call

interface NewsRepository {
    fun getArticlesBySource(source: String) : Call<ArticlesResponse>
}