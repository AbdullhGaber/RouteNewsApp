package com.example.newsapp.domain.usecases.sources_usecases

import com.example.newsapp.domain.models.SourcesResponse
import com.example.newsapp.domain.repositories.sources_repository.SourceRepository
import retrofit2.Call

class GetAllSourcesUseCase(
    val sourcesRepository: SourceRepository
){
    operator fun invoke() : Call<SourcesResponse>{
        return sourcesRepository.getAllSources()
    }
}