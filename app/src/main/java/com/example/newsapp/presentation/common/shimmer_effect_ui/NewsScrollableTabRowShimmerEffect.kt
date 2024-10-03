package com.example.newsapp.presentation.common.shimmer_effect_ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.utils.shimmerEffect


@Composable
fun NewsScrollableTabRowShimmerEffect(
    modifier: Modifier = Modifier,
){
    Row(
        modifier.fillMaxWidth()
    ){
        repeat(10){
            Box(
                modifier = Modifier.
                padding(8.dp).
                size(32.dp,16.dp).
                clip(RoundedCornerShape(10.dp)).
                shimmerEffect()
            )
        }
    }
}

@Composable
@Preview
fun PreviewNewsScrollableTabRow(){
    NewsScrollableTabRowShimmerEffect()
}