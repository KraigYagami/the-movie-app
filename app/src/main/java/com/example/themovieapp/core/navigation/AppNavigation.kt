package com.example.themovieapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.themovieapp.core.navigation.routes.account.accountNavigation
import com.example.themovieapp.core.navigation.routes.auth.AuthScreens
import com.example.themovieapp.core.navigation.routes.auth.authNavigation
import com.example.themovieapp.core.navigation.routes.favorite.favoriteNavigation
import com.example.themovieapp.core.navigation.routes.home.homeNavigation
import com.example.themovieapp.modules.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.Splash.route
    ) {
        composable(AppScreens.Splash.route) {
            SplashScreen(
                onNavigate = {
                    navController.navigate(AuthScreens.AuthNavigation.route)
                }
            )
        }
        authNavigation()
        homeNavigation()
        favoriteNavigation()
        accountNavigation()
    }
}
