package com.example.newsapp.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.models.Source
import com.example.newsapp.utils.Resource

data class HomeScreenState(
    val sourcesState: State<Resource<List<Source>>> = mutableStateOf(Resource.Unspecified()),
    val articlesState: State<Resource<List<Article>>> = mutableStateOf(Resource.Unspecified()),
    val category : State<String> = mutableStateOf("")
)
