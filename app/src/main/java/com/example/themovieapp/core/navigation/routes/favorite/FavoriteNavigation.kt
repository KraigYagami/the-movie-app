package com.example.themovieapp.core.navigation.routes.favorite

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

fun NavGraphBuilder.favoriteNavigation() {
    navigation(
        startDestination = FavoriteScreens.Favorite.route,
        route = FavoriteScreens.FavoriteNavigation.route
    ) {
        composable(FavoriteScreens.Favorite.route) {
//            FavoriteScreen()
        }
    }
}
