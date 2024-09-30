package com.example.newsapp.domain.repositories.sources_repository

import com.example.newsapp.domain.models.SourcesResponse
import retrofit2.Call


interface SourceRepository {
    fun getAllSources() : Call<SourcesResponse>
}