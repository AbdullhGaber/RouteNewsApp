package com.example.newsapp.presentation.home

sealed class HomeScreenEvents {
    data class GetAllArticles(val query : String , val source : String) : HomeScreenEvents()
    data object GetAllSources : HomeScreenEvents()
}