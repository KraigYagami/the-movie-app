package com.kraigcorp.themovieapp.core.navigation.routes.account

sealed class AccountScreens(val route: String) {
    object Account : AccountScreens("account")
}
