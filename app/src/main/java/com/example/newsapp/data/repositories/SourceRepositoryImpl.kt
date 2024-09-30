package com.example.newsapp.data.repositories

import com.example.newsapp.data.remote.NewsAPIService
import com.example.newsapp.domain.models.SourcesResponse
import com.example.newsapp.domain.repositories.sources_repository.SourceRepository
import retrofit2.Call
import javax.xml.transform.Source

class SourceRepositoryImpl(
    val newsAPIService : NewsAPIService
) : SourceRepository {
    override fun getAllSources(): Call<SourcesResponse> {
        return newsAPIService.getSources()
    }
}