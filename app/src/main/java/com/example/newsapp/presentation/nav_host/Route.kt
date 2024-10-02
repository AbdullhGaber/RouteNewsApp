package com.example.newsapp.presentation.nav_host

sealed class Route(val route : String) {
    data object StartNavigation : Route("startNavigation")
    data object CategoriesScreen : Route("categoriesScreen")
    data object NewsNavigation : Route("newsNavigation")
    data object HomeScreen : Route("homeScreen")
    data object SettingsScreen : Route("settingsScreen")
    data object DetailsScreen: Route("detailsScreen")
}