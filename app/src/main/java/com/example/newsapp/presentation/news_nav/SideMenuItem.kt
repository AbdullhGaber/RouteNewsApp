package com.example.newsapp.presentation.news_nav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.newsapp.R

data class SideMenuItem(
    @StringRes val name : Int,
    @DrawableRes val icon : Int
){
    companion object {
        val sideMenuItems = listOf(
            SideMenuItem(
                name = R.string.categories,
                icon = R.drawable.ic_menu
            ),

            SideMenuItem(
                name = R.string.settings,
                icon = R.drawable.ic_settings
            )
        )
    }
}


