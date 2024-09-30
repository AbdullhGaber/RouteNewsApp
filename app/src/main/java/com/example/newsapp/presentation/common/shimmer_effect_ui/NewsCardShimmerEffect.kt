package com.example.newsapp.presentation.common.shimmer_effect_ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.utils.shimmerEffect


@Composable
fun NewsCardShimmer(
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier.padding(20.dp)
    ){
        repeat(10){
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .shimmerEffect()
                    .height(250.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth(.2f)
                    .height(20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shimmerEffect()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shimmerEffect()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth(.2f)
                    .height(20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .align(Alignment.End)
                    .shimmerEffect()
            )
            
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
@Preview
fun PreviewNewsCard(){
    NewsCardShimmer()
}