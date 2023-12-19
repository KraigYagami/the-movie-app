package com.kraigcorp.themovieapp.core.navigation.routes.home

sealed class HomeScreens(val route: String) {
    object Home : HomeScreens("home")
    object MovieDetail : HomeScreens("movie_detail")
}
