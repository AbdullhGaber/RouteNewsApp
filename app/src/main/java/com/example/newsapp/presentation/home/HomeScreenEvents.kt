package com.example.newsapp.presentation.home

sealed class HomeScreenEvents {
    data class GetAllArticlesBySource(val source : String) : HomeScreenEvents()
    data object GetAllSources : HomeScreenEvents()
}