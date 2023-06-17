package com.example.themovieapp.core.navigation.routes.account

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.themovieapp.core.navigation.AppScreens

fun NavGraphBuilder.accountNavigation() {
    navigation(
        startDestination = AccountScreens.Account.route,
        route = AppScreens.AccountNavigation.route
    ) {
        composable(AccountScreens.Account.route) {
//            AccountScreen()
        }
    }
}
