package com.example.newsapp.domain.models

data class SourcesResponse(
    val sources: List<Source>? = listOf(),
    val status: String? = ""
)