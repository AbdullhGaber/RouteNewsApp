package com.example.newsapp.presentation.nav_host

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.presentation.categories.CategoriesScreen
import com.example.newsapp.presentation.news_nav.NewsNavigator

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
                        onCategoryCardClick = { queryName,name ->
                            navigateToTab(navController,Route.NewsNavigation.route+"/$queryName/$name")
                        }
                    )
                }
            }

        composable(
            route = Route.NewsNavigation.route+"/{categoryQuery}/{categoryName}",
        ){
            val categoryQuery = it.arguments?.getString("categoryQuery") ?: "sports"
            val categoryName = it.arguments?.getString("categoryName") ?: "sports"
            NewsNavigator(navController , categoryQuery , categoryName)
        }
    }

}

fun navigateToTab(navController: NavController , route : String){
    navController.navigate(route)
}


@Composable
@Preview
fun PreviewNewsNavHost(){
//    NewsNavHost()
}