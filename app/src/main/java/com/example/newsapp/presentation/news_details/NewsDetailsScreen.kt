package com.example.newsapp.presentation.news_details


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R
import com.example.newsapp.presentation.common.NewsCard
import com.example.newsapp.presentation.common.NewsTopBar

@Composable
fun NewsDetailsScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.pattern),
                contentScale = ContentScale.Crop
            )
    ){
        NewsTopBar(title = "News title")

        Spacer(modifier = Modifier.height(10.dp))

        NewsCard(
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        
        Text(
            text = "some content" ,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.align(Alignment.End)
        ){
            Text("View Full Article" , fontSize = 14.sp)
            Spacer(modifier = Modifier.width(10.dp))
            Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = null )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewNewsDetailsScreen(){
    NewsDetailsScreen()
}