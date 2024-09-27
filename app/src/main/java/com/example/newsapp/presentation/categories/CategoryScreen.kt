package com.example.newsapp.presentation.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R
import com.example.newsapp.presentation.categories.components.NewsCategoryLazyVerticalGrid

@Composable
fun CategoryScreen(){
    Column(
        modifier = Modifier.paint(
            painter = painterResource(id = R.drawable.pattern),
            contentScale = ContentScale.Crop
        ).fillMaxSize()
    ){
        Text(
            text = "Choose category of your interest",
            fontSize = 24.sp,
            modifier = Modifier.align(CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        NewsCategoryLazyVerticalGrid(
            onCardClick = {

            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCategoryScreen(){
    CategoryScreen()
}