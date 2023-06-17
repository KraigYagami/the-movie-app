package com.example.themovieapp.core.navigation.routes.home

sealed class HomeScreens(val route: String) {
    object HomeNavigation : HomeScreens("home_navigation")
    object Home : HomeScreens("home")
    object MovieDetail : HomeScreens("movie_detail")
}
