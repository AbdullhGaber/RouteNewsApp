package com.example.newsapp.presentation.news_nav

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import androidx.navigation.compose.rememberNavController
import com.example.newsapp.ui.theme.Green

@Composable
fun NewsNavigator(){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)

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
//        val navController = rememberNavController()
    }
}

@Composable
@Preview
fun PreviewNewsNavigator(){
    NewsNavigator()
}
