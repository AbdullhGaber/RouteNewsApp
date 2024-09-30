package com.example.newsapp.domain.models

data class ArticlesResponse(
    val articles: List<Article>? = null,
    val status: String? = null,
    val totalResults: Int? = null
)