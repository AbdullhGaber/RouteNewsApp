package com.example.newsapp.presentation.categories.components

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsapp.presentation.categories.Category
import com.example.newsapp.presentation.common.NewsCategoryCard

@Composable
fun NewsCategoryLazyVerticalGrid(
    onCardClick : () -> Unit
){
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        itemsIndexed(Category.categories){ index, category ->
            NewsCategoryCard(
                category = category,
                pos = index,
                onClick = {
                    onCardClick()
                }
            )
        }
    }
}

@Composable
@Preview
fun PreviewNewsCategoryLazyVerticalGrid(){
    NewsCategoryLazyVerticalGrid({})
}