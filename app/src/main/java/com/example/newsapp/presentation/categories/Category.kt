package com.example.newsapp.presentation.categories

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.newsapp.ui.theme.*
import com.example.newsapp.R

data class Category(
    @StringRes val name : Int,
    @DrawableRes val image : Int,
    val background : Color
){
    companion object {
         val categories = listOf(
            Category(
                name = R.string.sports,
                image = R.drawable.sports,
                background = Red
            ),

             Category(
                name = R.string.politics,
                image = R.drawable.politics,
                background = Blue
            ),

             Category(
                name = R.string.health,
                image = R.drawable.health,
                background = Pink
            ),

             Category(
                name = R.string.business,
                image = R.drawable.bussines,
                background = Brown
            ),

             Category(
                name = R.string.environment,
                image = R.drawable.environment,
                background = LightBlue
            ),

             Category(
                name = R.string.science,
                image = R.drawable.science,
                background = LightYellow
            ),
        )
    }
}