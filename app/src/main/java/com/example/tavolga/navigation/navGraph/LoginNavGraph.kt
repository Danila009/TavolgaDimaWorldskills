package com.example.tavolga.navigation.navGraph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tavolga.navigation.LOGIN_ROUTE
import com.example.tavolga.navigation.Screen
import com.example.tavolga.screen.loginScreen.LoginScreen

fun NavGraphBuilder.loginNavGraph(
    navController: NavController
) {
    navigation(
        startDestination = Screen.Login.route,
        route = LOGIN_ROUTE,
        builder = {
            composable(Screen.Login.route){
                LoginScreen(
                    navController = navController,
                    viewModelApi = hiltViewModel()
                )
            }
        }
    )
}