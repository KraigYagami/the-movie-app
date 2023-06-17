package com.example.themovieapp.core.navigation

sealed class AppScreens(val route: String) {
    object Home : AppScreens("home")
}
