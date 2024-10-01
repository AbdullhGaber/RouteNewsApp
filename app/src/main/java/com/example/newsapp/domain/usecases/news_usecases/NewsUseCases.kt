package com.example.newsapp.domain.usecases.news_usecases

data class NewsUseCases(
    val getArticlesUseCase: GetArticlesUseCase,
    val getArticlesByQueryUseCase: GetArticlesByQueryUseCase
)
