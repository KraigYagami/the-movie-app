package com.example.themovieapp.core.navigation.routes.favorite

sealed class FavoriteScreens(val route: String) {
    object FavoriteNavigation : FavoriteScreens("favorite_navigation")
    object Favorite : FavoriteScreens("favorite")
}
