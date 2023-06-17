package com.example.themovieapp.core.navigation.routes.account

sealed class AccountScreens(val route: String) {
    object AccountNavigation : AccountScreens("account_navigation")
    object Account : AccountScreens("account")
}
