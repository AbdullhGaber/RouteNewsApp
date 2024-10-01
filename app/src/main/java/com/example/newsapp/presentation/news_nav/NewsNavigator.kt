package com.example.newsapp.presentation.news_nav

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.newsapp.presentation.common.NewsTopBar
import com.example.newsapp.presentation.home.HomeScreen
import com.example.newsapp.presentation.home.HomeScreenEvents
import com.example.newsapp.presentation.home.HomeScreenState
import com.example.newsapp.presentation.home.HomeViewModel
import com.example.newsapp.presentation.nav_host.Route
//import androidx.navigation.compose.rememberNavController
import com.example.newsapp.ui.theme.Green

@Composable
fun NewsNavigator(
    navHostController : NavHostController,
    title : String
){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
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
                        text = "News App",
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
                NewsTopBar(title = title )
            }
        ){padding ->
            val newsNavController = rememberNavController()

            NavHost(navController = newsNavController, startDestination = Route.HomeScreen.route ){
                composable(
                    route = Route.HomeScreen.route
                ){
                    val homeViewModel = hiltViewModel<HomeViewModel>()

                    val articlesState = homeViewModel.articlesStateFlow
                    val sourcesState = homeViewModel.sourcesStateFlow

                    val homeScreenState = HomeScreenState(
                        category = remember {
                            mutableStateOf(title)
                        },
                        articlesState = articlesState.collectAsState(),
                        sourcesState = sourcesState.collectAsState()
                    )

                    HomeScreen(
                        homeScreenState = homeScreenState,
                        homeScreenEvents = homeViewModel::onEvent,
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
