package com.example.newsapp.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R
import com.example.newsapp.presentation.categories.Category

@Composable
fun NewsCategoryCard(
    modifier : Modifier = Modifier,
    category : Category,
    pos : Int,
    onClick : () -> Unit
){
    Card(
        modifier = modifier.padding(4.dp),
        onClick = onClick,
        shape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp,
            bottomEnd = if(pos % 2 == 0) 0.dp else 24.dp ,
            bottomStart = if(pos % 2 == 0) 24.dp else 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = category.background
        )
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp).align(CenterHorizontally)
        ){
            Image(
                painter = painterResource(id = category.image),
                contentDescription = stringResource(id = R.string.sports),
                modifier = Modifier.align(CenterHorizontally).height(150.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = stringResource(id = category.name),
                Modifier.align(CenterHorizontally),
                color = Color.White,
                fontSize = 24.sp
            )
        }
    }
}

@Composable
@Preview
fun PreviewNewsCategoryCard(){
    NewsCategoryCard(
        pos = 0 ,
        category = Category.categories[0],
        onClick = {}
    )
}