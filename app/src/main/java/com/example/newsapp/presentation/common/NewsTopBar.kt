package com.example.newsapp.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R
import com.example.newsapp.ui.theme.Green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopBar(
    modifier : Modifier = Modifier,
    title : String,
    showNavIcon : Boolean = false,
    showSearchIcon : Boolean = false,
    onNavIconClick :  () -> Unit ={},
    onActionIconClick : () -> Unit = {}
){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White,
                fontSize = 24.sp
            )
        },

        actions = {
            if(showSearchIcon){
                Image(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Top bar menu",
                    modifier = Modifier.padding(end = 16.dp).clickable{
                        onActionIconClick()
                    }
                )
            }
        },

        modifier = modifier.clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)),

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Green,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        ),

        navigationIcon = {
            if(showNavIcon){
                Image(
                    painter = painterResource(id = R.drawable.ic_menu_top_bar),
                    contentDescription = "Top bar menu",
                    modifier = Modifier.padding(start = 8.dp).clickable{
                        onNavIconClick()
                    }
                )
            }
        },
    )
}

@Composable
@Preview
fun PreviewNewsTopBar(){
//    NewsTopBar(title = "News App")
}