package com.example.newsapp.presentation.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.newsapp.domain.models.Article
import com.example.newsapp.utils.Resource

data class SearchScreenState(
    val query : MutableState<String> = mutableStateOf(""),
    val articlesState : State<Resource<List<Article>>> = mutableStateOf(Resource.Unspecified())
)