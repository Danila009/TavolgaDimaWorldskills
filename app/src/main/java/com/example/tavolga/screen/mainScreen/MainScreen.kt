package com.example.tavolga.screen.mainScreen

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.tavolga.api.ViewModelApi
import com.example.tavolga.screen.mainScreen.view.ButtonBarData
import com.example.tavolga.screen.mainScreen.view.buttonBarScreen.HomeScreen
import com.example.tavolga.ui.theme.background

@Composable
fun MainScreen(
    navController: NavController,
    viewModelApi: ViewModelApi,
    owner: LifecycleOwner
) {
    val idBar = remember { mutableStateOf("Home") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                backgroundColor = background
            )
        }, bottomBar = {
            BottomNavigation(backgroundColor = background) {
                ButtonBarData.values().forEach { data ->
                    BottomNavigationItem(
                        selected = idBar.value == data.name,
                        onClick = { idBar.value = data.name },
                        label = {
                            Text(text = data.title)
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = data.image),
                                contentDescription = null,
                                modifier = Modifier.size(25.dp)
                            )
                        }, selectedContentColor = Color.Black,
                        unselectedContentColor = Color.White
                    )
                }
            }
        }, content = {
            when(idBar.value){
                "Profile"->{}
                "Home"-> HomeScreen(
                    navController = navController,
                    viewModelApi = viewModelApi,
                    owner = owner
                )
                "Event"->{}
            }
        }
    )
}