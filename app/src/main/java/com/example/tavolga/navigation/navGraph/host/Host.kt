package com.example.tavolga.navigation.navGraph.host

import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.tavolga.navigation.LOGIN_ROUTE
import com.example.tavolga.navigation.ROUTE
import com.example.tavolga.navigation.navGraph.loginNavGraph
import com.example.tavolga.navigation.navGraph.mainNavGraph

@Composable
fun Host(
    navHostController: NavHostController,
    owner: LifecycleOwner
) {
    NavHost(
        navController = navHostController,
        startDestination = LOGIN_ROUTE,
        route = ROUTE,
        builder = {
            loginNavGraph(
                navController = navHostController
            )
            mainNavGraph(
                navController = navHostController,
                owner = owner
            )
        }
    )
}