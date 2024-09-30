package com.example.newsapp.di

import com.example.newsapp.data.remote.NewsAPIService
import com.example.newsapp.data.repositories.NewsRepositoryImpl
import com.example.newsapp.data.repositories.SourceRepositoryImpl
import com.example.newsapp.domain.repositories.news_repository.NewsRepository
import com.example.newsapp.domain.repositories.sources_repository.SourceRepository
import com.example.newsapp.domain.usecases.news_usecases.GetAllNewsBySourceUseCase
import com.example.newsapp.domain.usecases.news_usecases.NewsUseCases
import com.example.newsapp.domain.usecases.sources_usecases.GetAllSourcesUseCase
import com.example.newsapp.domain.usecases.sources_usecases.SourcesUseCases
import com.example.newsapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideHttpClient() : OkHttpClient{
         val clientBuilder = OkHttpClient.Builder().addInterceptor{ chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader(Constants.AUTH_KEY, Constants.API_KEY)
                .build()
            chain.proceed(request)
        }

        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.NEWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsAPIService(
        retrofit: Retrofit
    ) : NewsAPIService = retrofit.create(NewsAPIService::class.java)

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsAPIService: NewsAPIService
    ) : NewsRepository{
         return NewsRepositoryImpl(newsAPIService)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ) : NewsUseCases{
        return NewsUseCases(
            getAllNewsBySourceUseCase = GetAllNewsBySourceUseCase(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideSourcesRepository(
        newsAPIService: NewsAPIService
    ) : SourceRepository{
        return SourceRepositoryImpl(newsAPIService)
    }

    @Provides
    @Singleton
    fun provideSourcesUseCases(
        sourceRepository: SourceRepository
    ) : SourcesUseCases{
        return SourcesUseCases(
            getAllSourcesUseCase = GetAllSourcesUseCase(sourceRepository)
        )
    }
}