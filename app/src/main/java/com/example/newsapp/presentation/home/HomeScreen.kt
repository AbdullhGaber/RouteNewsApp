package com.example.newsapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R
import com.example.newsapp.domain.models.news
import com.example.newsapp.presentation.common.NewsCard
import com.example.newsapp.presentation.common.NewsScrollableTabRow
import com.example.newsapp.presentation.common.NewsTopBar

@Composable
fun HomeScreen(){
    Column{
        NewsTopBar(title = "Sports")

        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                .paint(painterResource(id = R.drawable.pattern), contentScale = ContentScale.Crop)
        ){
            NewsScrollableTabRow()

            LazyColumn{
                items(news){
                   NewsCard(
                       modifier = Modifier.padding(horizontal = 16.dp),
                       news = it
                   )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHomeScreen(){
    HomeScreen()
}