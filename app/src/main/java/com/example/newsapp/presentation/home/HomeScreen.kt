package com.example.newsapp.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsapp.R
import com.example.newsapp.presentation.common.NewsCard
import com.example.newsapp.presentation.common.NewsScrollableTabRow
import com.example.newsapp.presentation.common.NewsTopBar
import com.example.newsapp.presentation.common.shimmer_effect_ui.NewsCardShimmer
import com.example.newsapp.presentation.common.shimmer_effect_ui.NewsScrollableTabRowShimmerEffect
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    modifier : Modifier = Modifier,
    homeScreenState : HomeScreenState,
    homeScreenEvents: (HomeScreenEvents) -> Unit = {}
){

    val articles = homeScreenState.articlesState.value.data
    val sources = homeScreenState.sourcesState.value.data
    val listState = rememberLazyListState() // Added LazyListState

    LaunchedEffect(Unit) {
        homeScreenEvents(HomeScreenEvents.GetAllArticles(homeScreenState.category.value,"abc-news"))
    }

    Column{
        NewsTopBar(title = homeScreenState.category.value)

        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                .paint(painterResource(id = R.drawable.pattern), contentScale = ContentScale.Crop)
        ){

            if(homeScreenState.sourcesState.value is Resource.Loading){
                NewsScrollableTabRowShimmerEffect()
            }

            if(homeScreenState.articlesState.value is Resource.Loading){
                NewsCardShimmer()
            }

            sources?.let{
                NewsScrollableTabRow(
                    sources = sources,
                    onItemClick = { source ->
                        homeScreenEvents(HomeScreenEvents.GetAllArticles(homeScreenState.category.value,source))
                    }
                )
            }

            articles?.let{
                LazyColumn(
                    state = listState,
                ){
                    items(articles){
                        NewsCard(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            article = it
                        )
                    }
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewHomeScreen(){
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val articlesState = homeViewModel.articlesStateFlow
    val sourcesState = homeViewModel.sourcesStateFlow

    val homeScreenState = HomeScreenState(
        articlesState = articlesState.collectAsState(),
        sourcesState = sourcesState.collectAsState()
    )

    HomeScreen(
        homeScreenState = homeScreenState,
        homeScreenEvents = homeViewModel::onEvent
    )
}