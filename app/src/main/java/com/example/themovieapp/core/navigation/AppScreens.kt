package com.example.themovieapp.core.navigation

sealed class AppScreens(val route: String) {
    object Splash : AppScreens("splash")
    object AccountNavigation : AppScreens("account_navigation")
    object AuthNavigation : AppScreens("auth_navigation")
    object FavoriteNavigation : AppScreens("favorite_navigation")
    object HomeNavigation : AppScreens("home_navigation")
}
