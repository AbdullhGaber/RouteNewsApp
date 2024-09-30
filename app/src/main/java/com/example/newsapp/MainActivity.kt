package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsapp.presentation.home.HomeScreen
import com.example.newsapp.presentation.home.HomeScreenState
import com.example.newsapp.presentation.home.HomeViewModel
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                val homeViewModel = hiltViewModel<HomeViewModel>()

                val articlesState = homeViewModel.articlesStateFlow
                val sourcesState = homeViewModel.sourcesStateFlow

                val homeScreenState = HomeScreenState(
                    articlesState = articlesState.collectAsState(),
                    sourcesState = sourcesState.collectAsState()
                )

                HomeScreen(
                    homeScreenState,
                    homeViewModel::onEvent
                )
            }
        }
    }
}

