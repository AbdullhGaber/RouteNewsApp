package com.example.newsapp.presentation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.domain.models.Article
import com.example.newsapp.presentation.common.NewsCard
import com.example.newsapp.presentation.common.shimmer_effect_ui.NewsCardShimmer
import com.example.newsapp.utils.Resource

@Composable
fun SearchScreen(
    modifier : Modifier = Modifier,
    searchScreenState: SearchScreenState = SearchScreenState(),
    navigateToScreen : (Article) -> Unit = {}
){
    val articles = searchScreenState.articlesState.value.data

    Column(
        modifier = modifier.fillMaxSize()
            .paint(painterResource(id = R.drawable.pattern), contentScale = ContentScale.Crop)
    ) {
        if(searchScreenState.articlesState.value is Resource.Loading){
            NewsCardShimmer()
        }

        if(searchScreenState.articlesState.value is Resource.Success && searchScreenState.articlesState.value.data?.isEmpty()!!){
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().weight(1f)
            ){
                Image(
                    painter = painterResource(id = R.drawable.no_results),
                    contentDescription = "No results",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(350.dp)
                )
            }
        }

        articles?.let{
            if(articles.isNotEmpty()){
                LazyColumn{
                    items(articles){
                        NewsCard(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            article = it,
                            onClick = {
                                navigateToScreen(it)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewSearchScreen(){
    SearchScreen()
}