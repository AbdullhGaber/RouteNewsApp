package com.example.newsapp.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.models.ArticlesResponse
import com.example.newsapp.domain.usecases.news_usecases.NewsUseCases
import com.example.newsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val newsUseCases: NewsUseCases
) : ViewModel() {
    private val _articlesStateFlow = MutableStateFlow<Resource<List<Article>>>(Resource.Unspecified())
    val articlesStateFlow = _articlesStateFlow.asStateFlow()

    fun getArticlesByQuery(query : String){
        viewModelScope.launch {
            _articlesStateFlow.emit(
                Resource.Loading()
            )
        }
        newsUseCases.getArticlesByQueryUseCase(query).enqueue(
            object : Callback<ArticlesResponse>{
                override fun onResponse(
                    call: Call<ArticlesResponse>,
                    response: Response<ArticlesResponse>,
                ) {
                    if(response.body() != null){
                        val articles = response.body()?.articles!!
                        viewModelScope.launch {
                            _articlesStateFlow.emit(
                                Resource.Success(data = articles)
                            )
                        }
                    }else{
                        Log.e("API" , response.code().toString())
                        Log.e("API" , response.message().toString())
                        Log.e("API" , response.errorBody()?.string()!!)
                    }
                }

                override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                    viewModelScope.launch {
                        _articlesStateFlow.emit(
                            Resource.Failure(message = t.message.toString())
                        )
                        Log.e("API" , t.message.toString())
                    }
                }
            }
        )
    }
}