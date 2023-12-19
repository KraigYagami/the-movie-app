package com.kraigcorp.themovieapp.core.navigation.routes.favorite

sealed class FavoriteScreens(val route: String) {
    object Favorite : FavoriteScreens("favorite")
}
