package com.example.newsapp.domain.usecases.news_usecases

import com.example.newsapp.domain.models.ArticlesResponse
import com.example.newsapp.domain.repositories.news_repository.NewsRepository
import retrofit2.Call

class GetArticlesByQueryUseCase(
    val newsRepository: NewsRepository
) {
    operator fun invoke(query : String) : Call<ArticlesResponse>{
        return newsRepository.getArticlesByQuery(query)
    }
}