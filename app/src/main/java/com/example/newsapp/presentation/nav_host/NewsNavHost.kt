package com.example.newsapp.presentation.nav_host

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.presentation.categories.CategoriesScreen
import com.example.newsapp.presentation.news_nav.NewsNavigator

@Composable
fun NewsNavHost(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.StartNavigation.route ){
            navigation(
                route = Route.StartNavigation.route,
                startDestination = Route.CategoriesScreen.route
            ){
                composable(
                    route = Route.CategoriesScreen.route
                ){
                    CategoriesScreen(
                        onCategoryCardClick = {
                            navigateToTab(navController,Route.NewsNavigation.route+"/$it")
                        }
                    )
                }
            }

        composable(
            route = Route.NewsNavigation.route+"/{category}",
        ){
            val category = it.arguments?.getString("category") ?: "sports"
            NewsNavigator(navController , category)
        }
    }

}

fun navigateToTab(navController: NavController , route : String){
    navController.navigate(route)
}


@Composable
@Preview
fun PreviewNewsNavHost(){
    NewsNavHost()
}