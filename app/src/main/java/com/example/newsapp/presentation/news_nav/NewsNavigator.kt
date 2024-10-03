package com.example.newsapp.presentation.news_nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.R
import com.example.newsapp.domain.models.Article
import com.example.newsapp.presentation.common.NewsSearchBar
import com.example.newsapp.presentation.common.NewsTopBar
import com.example.newsapp.presentation.home.HomeScreen
import com.example.newsapp.presentation.home.HomeScreenState
import com.example.newsapp.presentation.home.HomeViewModel
import com.example.newsapp.presentation.nav_host.Route
import com.example.newsapp.presentation.news_details.NewsDetailsScreen
import com.example.newsapp.presentation.search.SearchScreen
import com.example.newsapp.presentation.search.SearchScreenState
import com.example.newsapp.presentation.search.SearchViewModel
import com.example.newsapp.presentation.settings.SettingsScreen
import com.example.newsapp.ui.theme.Green
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
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

    val topBarContent : MutableState<@Composable () -> Unit> = remember {
        mutableStateOf(
            @Composable {
                    Text(
                        text = titleState.value,
                        color = Color.White,
                        fontSize = 24.sp
                    )
            }
        )
    }

    val showNavIcon = remember {
        mutableStateOf(true)
    }

    val showSearchIcon = remember {
        mutableStateOf(true)
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
                    content = topBarContent.value ,
                    onNavIconClick = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    },
                    onActionIconClick = {
                        newsNavController.navigate(Route.SearchScreen.route)
                    },
                    showSearchIcon = showSearchIcon.value,
                    showNavIcon = showNavIcon.value
                )
            }
        ){padding ->
            NavHost(navController = newsNavController, startDestination = Route.HomeScreen.route ){
                composable(
                    route = Route.HomeScreen.route
                ){
                    topBarContent.value = {
                        Text(
                            text = titleState.value,
                            color = Color.White,
                            fontSize = 24.sp
                        )
                    }
                    showNavIcon.value = true
                    showSearchIcon.value = true

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
                    topBarContent.value = {
                        Text(
                            text = stringResource(R.string.article_details),
                            color = Color.White,
                            fontSize = 24.sp
                        )
                    }
                    showNavIcon.value = false
                    showSearchIcon.value = false
                    NewsDetailsScreen(
                        modifier = Modifier.padding(padding),
                        article = article
                    )
                }

                composable(
                    route = Route.SettingsScreen.route
                ){
                    showNavIcon.value = true
                    showSearchIcon.value = false
                    titleState.value = stringResource(R.string.settings)
                    SettingsScreen(
                        modifier = Modifier.padding(padding)
                    )
                }

                composable(
                    route = Route.SearchScreen.route
                ) { navBackStackEntry ->
                    val searchViewModel : SearchViewModel = hiltViewModel()
                    val searchScreenState = SearchScreenState(
                        articlesState = searchViewModel.articlesStateFlow.collectAsState(),
                    )
                    topBarContent.value = {
                         Column{
                             NewsSearchBar(
                                 query = searchScreenState.query,
                                 onSearch = {
                                     searchViewModel.getArticlesByQuery(searchScreenState.query.value)
                                 },
                                 onCloseIconClick = {
                                     newsNavController.navigateUp()
                                     topBarContent.value = {
                                         Text(
                                             text = titleState.value,
                                             color = Color.White,
                                             fontSize = 24.sp
                                         )
                                     }
                                     showNavIcon.value = true
                                     showSearchIcon.value = true
                                 }
                             )
                             Spacer(modifier = Modifier.height(16.dp))
                         }
                    }
                    showNavIcon.value = false
                    showSearchIcon.value = false
                    SearchScreen(
                        searchScreenState = searchScreenState,
                        navigateToScreen = {
                            newsNavController.currentBackStackEntry?.savedStateHandle?.set("article",it)
                            newsNavController.navigate(Route.DetailsScreen.route)
                        },
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
