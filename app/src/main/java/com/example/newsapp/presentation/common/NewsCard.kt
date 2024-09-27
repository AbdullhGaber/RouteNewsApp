package com.example.newsapp.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R
import com.example.newsapp.domain.models.News
import com.example.newsapp.domain.models.news

@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    news : News
){
    Column(
        modifier.padding(vertical = 24.dp)
    ){
        Image(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.news_1),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = news.newsCategory , fontSize = 8.sp , color = Color.Gray)

        Spacer(modifier = Modifier.height(5.dp))

        Text(text = news.title , fontSize = 16.sp)

        Text(
            text = news.timestamps,
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Composable
@Preview
fun PreviewNewsCard(){
    NewsCard(news = news[0])
}