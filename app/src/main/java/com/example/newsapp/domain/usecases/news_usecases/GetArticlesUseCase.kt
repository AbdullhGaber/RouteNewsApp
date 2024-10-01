package com.example.newsapp.domain.usecases.news_usecases

import com.example.newsapp.domain.models.ArticlesResponse
import com.example.newsapp.domain.repositories.news_repository.NewsRepository
import retrofit2.Call

class GetArticlesUseCase(
    val newsRepository: NewsRepository
){
    operator fun invoke(query : String ,source : String ) : Call<ArticlesResponse>{
        return newsRepository.getArticles(query,source)
    }
}