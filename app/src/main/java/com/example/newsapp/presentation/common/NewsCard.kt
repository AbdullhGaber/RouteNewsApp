package com.example.newsapp.presentation.common

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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.Placeholder
import com.bumptech.glide.integration.compose.placeholder
import com.example.newsapp.R
import com.example.newsapp.domain.models.Article

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    article: Article? = null
){
    Column(
        modifier.padding(vertical = 24.dp)
    ){
        GlideImage(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth(),
            model = article?.urlToImage,
            contentDescription = article?.description,
            contentScale = ContentScale.Crop,
            loading = placeholder(painter = painterResource(id = R.drawable.placeholder_news))
        )


        Spacer(modifier = Modifier.height(10.dp))

        Text(text = article?.source?.name ?: "source" , fontSize = 8.sp , color = Color.Gray)

        Spacer(modifier = Modifier.height(5.dp))

        Text(text = article?.title ?: "title" , fontSize = 16.sp)

        Text(
            text = article?.publishedAt ?: "3 hours ago",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Composable
@Preview
fun PreviewNewsCard(){
    NewsCard()
}