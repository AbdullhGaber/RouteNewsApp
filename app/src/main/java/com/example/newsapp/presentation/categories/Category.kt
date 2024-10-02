package com.example.newsapp.presentation.categories

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.example.newsapp.ui.theme.*
import com.example.newsapp.R

data class Category(
    @StringRes val nameID : Int,
    val name : String,
    @DrawableRes val image : Int,
    val background : Color
){
    companion object {
         val categories = listOf(
            Category(
                name = "Sports",
                nameID = R.string.sports,
                image = R.drawable.sports,
                background = Red
            ),

             Category(
                name = "Politics",
                nameID = R.string.politics,
                image = R.drawable.politics,
                background = Blue
            ),

             Category(
                 name = "Health",
                nameID = R.string.health,
                image = R.drawable.health,
                background = Pink
            ),

             Category(
                name = "Business",
                nameID = R.string.business,
                image = R.drawable.bussines,
                background = Brown
            ),

             Category(
                name = "Environment",
                nameID = R.string.environment,
                image = R.drawable.environment,
                background = LightBlue
            ),

             Category(
                name = "Science",
                nameID = R.string.science,
                image = R.drawable.science,
                background = LightYellow
            ),
        )
    }
}