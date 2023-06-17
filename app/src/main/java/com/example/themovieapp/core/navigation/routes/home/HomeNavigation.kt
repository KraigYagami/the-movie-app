package com.example.themovieapp.core.navigation.routes.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.themovieapp.modules.home.HomeScreen

fun NavGraphBuilder.homeNavigation() {
    navigation(
        startDestination = HomeScreens.Home.route,
        route = HomeScreens.HomeNavigation.route
    ) {
        composable(HomeScreens.Home.route) {
            HomeScreen()
        }
        composable(HomeScreens.MovieDetail.route) {
//            MovieDetailScreen()
        }
    }
}
