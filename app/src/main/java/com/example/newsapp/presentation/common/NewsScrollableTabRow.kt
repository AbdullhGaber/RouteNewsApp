package com.example.newsapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.ui.theme.Green

val categories = listOf(
    "ABC news",
    "Jazira news",
    "BBC news",
    "CNN news"
)

@Composable
fun NewsScrollableTabRow(
    modifier: Modifier = Modifier
){
    val selectedIdx = remember {
        mutableIntStateOf(0)
    }

    ScrollableTabRow(
        modifier = modifier,
        selectedTabIndex = selectedIdx.intValue,
        edgePadding = 0.dp,
        indicator = {},
        containerColor = Color.Transparent,
        divider = {}
    ){
        categories.forEachIndexed { index, category ->
            val isSelected = index == selectedIdx.intValue
            Box(
                modifier = Modifier.
                padding(8.dp).
                clip(RoundedCornerShape(10.dp)).
                background(color = if(isSelected) Green else Color.White).
                border(
                    brush = Brush.linearGradient(if(isSelected) listOf(Color.Transparent, Color.Transparent) else listOf(Green , Green)),
                    width = 1.dp ,
                    shape = RoundedCornerShape(10.dp)
                ).
                padding(vertical = 4.dp, horizontal = 8.dp).
                clickable { selectedIdx.intValue = index }
            ){
                Text(
                    text = category,
                    color = if(isSelected) Color.White else Green,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewNewsScrollableTabRow(){
    NewsScrollableTabRow()
}