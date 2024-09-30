package com.example.newsapp.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.models.ArticlesResponse
import com.example.newsapp.domain.models.Source
import com.example.newsapp.domain.models.SourcesResponse
import com.example.newsapp.domain.usecases.news_usecases.NewsUseCases
import com.example.newsapp.domain.usecases.sources_usecases.SourcesUseCases
import com.example.newsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val mNewsUseCases: NewsUseCases,
    val mSourcesUseCases: SourcesUseCases
): ViewModel() {

    private val _articlesStateFlow = MutableStateFlow<Resource<List<Article>>>(Resource.Unspecified())
    val articlesStateFlow = _articlesStateFlow.asStateFlow()

    private val _sourcesStateFlow = MutableStateFlow<Resource<List<Source>>>(Resource.Unspecified())
    val sourcesStateFlow = _sourcesStateFlow.asStateFlow()

    init {
        onEvent(HomeScreenEvents.GetAllSources)
        onEvent(HomeScreenEvents.GetAllArticlesBySource("abc-news"))
    }

    fun onEvent(event : HomeScreenEvents){
        when(event){
            is HomeScreenEvents.GetAllArticlesBySource -> {
                getArticlesBySource(event.source)
            }

            is HomeScreenEvents.GetAllSources -> {
                getSources()
            }
        }
    }

    private fun getSources(){
        viewModelScope.launch(Dispatchers.IO){
            _sourcesStateFlow.emit(
                Resource.Loading()
            )
            mSourcesUseCases.getAllSourcesUseCase().enqueue(
                object : Callback<SourcesResponse>{
                    override fun onResponse(
                        call: Call<SourcesResponse>,
                        response: Response<SourcesResponse>,
                    ) {
                        if(response.body() != null){
                            val sources = response.body()!!.sources
                            viewModelScope.launch{
                                _sourcesStateFlow.emit(
                                    Resource.Success(sources)
                                )
                            }
                            Log.e("body : ", response.body().toString())
                            Log.e("code", response.code().toString())
                        }else{
                            Log.e("body : ", response.body().toString())
                        }
                    }

                    override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                        viewModelScope.launch {
                            _sourcesStateFlow.emit(
                                Resource.Failure(message = t.message.toString())
                            )
                            Log.e("API ERROR", t.message.toString())
                            Log.e("API ERROR", call.toString())
                        }
                    }

                }
            )
        }
    }

    private fun getArticlesBySource(source : String){
        viewModelScope.launch(Dispatchers.IO){
            _articlesStateFlow.emit(
                Resource.Loading()
            )
            mNewsUseCases.getAllNewsBySourceUseCase(source).enqueue(
                object : Callback<ArticlesResponse>{
                    override fun onResponse(
                        call: Call<ArticlesResponse>,
                        response: Response<ArticlesResponse>,
                    ) {
                        if(response.body() != null){
                            val articles = response.body()!!.articles
                            viewModelScope.launch{
                                _articlesStateFlow.emit(Resource.Success(articles))
                            }
                        }else{
                            Log.e("API ERROR", "body is null")
                            Log.e("API ERROR - is successful" , response.isSuccessful.toString())
                            Log.e("API ERROR - status code" , response.code().toString())
                        }
                    }

                    override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                        viewModelScope.launch {
                            _articlesStateFlow.emit(
                                Resource.Failure(message = t.message.toString())
                            )
                            Log.e("API ERROR", t.message.toString())
                            Log.e("API ERROR", call.toString())
                        }
                    }
                }
            )
        }
    }
}