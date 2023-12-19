package com.kraigcorp.themovieapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kraigcorp.themovieapp.core.auth.GoogleAuthUiClient
import com.kraigcorp.themovieapp.core.auth.GoogleEmailAuthClient
import com.kraigcorp.themovieapp.core.navigation.routes.account.accountNavigation
import com.kraigcorp.themovieapp.core.navigation.routes.auth.authNavigation
import com.kraigcorp.themovieapp.core.navigation.routes.favorite.favoriteNavigation
import com.kraigcorp.themovieapp.core.navigation.routes.home.homeNavigation
import com.kraigcorp.themovieapp.modules.splash.SplashScreen

@Composable
fun AppNavigation(
    googleAuthUiClient: GoogleAuthUiClient,
    googleEmailAuthClient: GoogleEmailAuthClient
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.Splash.route
    ) {
        composable(AppScreens.Splash.route) {
            SplashScreen(
                onNavigate = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.AuthNavigation.route)
                }
            )
        }
        authNavigation(
            navController = navController,
            googleAuthUiClient = googleAuthUiClient,
            googleEmailAuthClient = googleEmailAuthClient
        )
        homeNavigation(navController = navController, googleAuthUiClient = googleAuthUiClient)
        favoriteNavigation()
        accountNavigation()
    }
}
