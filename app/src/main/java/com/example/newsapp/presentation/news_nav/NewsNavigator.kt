package com.example.newsapp.presentation.news_nav

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.R
import com.example.newsapp.domain.models.Article
import com.example.newsapp.presentation.common.NewsTopBar
import com.example.newsapp.presentation.home.HomeScreen
import com.example.newsapp.presentation.home.HomeScreenState
import com.example.newsapp.presentation.home.HomeViewModel
import com.example.newsapp.presentation.nav_host.Route
import com.example.newsapp.presentation.news_details.NewsDetailsScreen
import com.example.newsapp.presentation.settings.SettingsScreen
import com.example.newsapp.ui.theme.Green
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NewsNavigator(
    navHostController : NavHostController,
    titleAsQuery : String,
    title : String
){
    val titleState = remember {
        mutableStateOf(title)
    }

    val titleQueryState = remember {
        mutableStateOf(titleAsQuery)
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val newsNavController = rememberNavController()
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Box(
                    modifier = Modifier
                        .height(110.dp)
                        .fillMaxWidth()
                        .background(color = Green)
                ){
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontSize = 24.sp,
                        color = Color.White,
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                val selectedIdx = remember {
                    mutableIntStateOf(0)
                }

                SideMenuItem.sideMenuItems.forEachIndexed { index, sideMenuItem ->
                    NavigationDrawerItem(
                        label = {
                            Text(text = stringResource(id = sideMenuItem.name))
                        },
                        selected = index == selectedIdx.intValue,
                        onClick = {
                            selectedIdx.intValue = index
                            when(index){
                                0 -> {
                                    navHostController.navigate(Route.StartNavigation.route)
                                    coroutineScope.launch {
                                        drawerState.close()
                                    }
                                }

                                1 -> {
                                    newsNavController.navigate(Route.SettingsScreen.route)
                                    coroutineScope.launch {
                                        drawerState.close()
                                    }
                                }
                            }
                        },
                        icon = {
                            Image(
                                painter = painterResource(id = sideMenuItem.icon),
                                contentDescription = "Side menu item"
                            )
                        }
                    )
                }
            }
        },
        drawerState = drawerState,
        gesturesEnabled = false
    ){
        Scaffold(
            topBar = {
                NewsTopBar(
                    title = titleState.value,
                    onNavIconClick = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    },
                    showSearchIcon = true,
                    showNavIcon = true
                )
            }
        ){padding ->
            NavHost(navController = newsNavController, startDestination = Route.HomeScreen.route ){
                composable(
                    route = Route.HomeScreen.route
                ){
                    val homeViewModel = hiltViewModel<HomeViewModel>()

                    val articlesState = homeViewModel.articlesStateFlow
                    val sourcesState = homeViewModel.sourcesStateFlow

                    val homeScreenState = HomeScreenState(
                        category = titleQueryState,
                        articlesState = articlesState.collectAsState(),
                        sourcesState = sourcesState.collectAsState()
                    )

                    HomeScreen(
                        modifier = Modifier.padding(padding),
                        homeScreenState = homeScreenState,
                        homeScreenEvents = homeViewModel::onEvent,
                        navigateToScreen = {
                            newsNavController.currentBackStackEntry?.savedStateHandle?.set("article" , it)
                            newsNavController.navigate(Route.DetailsScreen.route)
                        }
                    )
                }

                composable(
                    route = Route.DetailsScreen.route,
                ){
                    val article : Article? = newsNavController.previousBackStackEntry?.savedStateHandle?.get("article")
                    NewsDetailsScreen(article)
                }

                composable(
                    route = Route.SettingsScreen.route
                ){
                    titleState.value = stringResource(R.string.settings)
                    SettingsScreen(
                        modifier = Modifier.padding(padding)
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewNewsNavigator(){
//    NewsNavigator()
}
