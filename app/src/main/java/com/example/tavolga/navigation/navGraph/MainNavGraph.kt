package com.example.tavolga.navigation.navGraph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.tavolga.navigation.*
import com.example.tavolga.screen.mainScreen.CriterieScreen
import com.example.tavolga.screen.mainScreen.EventOpenScreen
import com.example.tavolga.screen.mainScreen.MainScreen
import com.example.tavolga.screen.mainScreen.NominationScreen
import com.example.tavolga.screen.mainScreen.view.RatingScreen

fun NavGraphBuilder.mainNavGraph(
    navController: NavController,
    owner: LifecycleOwner
) {
    navigation(
        startDestination = Screen.Main.route,
        route = MAIN_ROUTE,
        builder = {
            composable(Screen.Main.route){
                MainScreen(
                    navController = navController,
                    viewModelApi = hiltViewModel(),
                    owner = owner
                )
            }
            composable(Screen.EventOpen.route,
            arguments = listOf(
                navArgument(
                    name = EVENT_ARGUMENT,
                    builder = {
                        type = NavType.StringType
                    }
                )
            )){
                EventOpenScreen(
                    navController = navController,
                    eventString = it.arguments?.getString(EVENT_ARGUMENT).toString(),
                )
            }
            composable(Screen.Nomination.route,
            arguments = listOf(
                navArgument(NOMINATION_ARGUMENT){
                    type = NavType.StringType
                },
                navArgument(EVENT_NAME_ARGUMENT){
                    type = NavType.StringType
                }
            )){
                NominationScreen(
                    navController = navController,
                    eventName = it.arguments?.getString(EVENT_NAME_ARGUMENT).toString(),
                    nominationString = it.arguments?.getString(NOMINATION_ARGUMENT).toString()
                )
            }
            composable(Screen.Rating.route, arguments = listOf(
                navArgument(NOMINATION_ARGUMENT){
                    type = NavType.StringType
                },
                navArgument(EVENT_NAME_ARGUMENT){
                    type = NavType.StringType
                }
            )){
                RatingScreen(
                    navController = navController,
                    nominationString = it.arguments?.getString(NOMINATION_ARGUMENT).toString(),
                    eventName = it.arguments?.getString(EVENT_NAME_ARGUMENT).toString()
                )
            }
            composable(Screen.Criterie.route, arguments = listOf(
                navArgument(CRITERIE_ARGUMENT){
                    type = NavType.StringType
                }, navArgument(USERNAME_ARGUMENT){
                    type = NavType.StringType
                }
            )){
                CriterieScreen(
                    navController = navController,
                    criterieString = it.arguments?.getString(CRITERIE_ARGUMENT).toString(),
                    userName = it.arguments?.getString(USERNAME_ARGUMENT).toString()
                )
            }
        }
    )
}