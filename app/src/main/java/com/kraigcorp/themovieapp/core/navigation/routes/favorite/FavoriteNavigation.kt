package com.kraigcorp.themovieapp.core.navigation.routes.favorite

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.kraigcorp.themovieapp.core.navigation.AppScreens

fun NavGraphBuilder.favoriteNavigation() {
    navigation(
        startDestination = FavoriteScreens.Favorite.route,
        route = AppScreens.FavoriteNavigation.route
    ) {
        composable(FavoriteScreens.Favorite.route) {
//            FavoriteScreen()
        }
    }
}
