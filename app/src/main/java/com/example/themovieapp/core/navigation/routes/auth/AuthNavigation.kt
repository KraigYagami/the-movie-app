package com.example.themovieapp.core.navigation.routes.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.themovieapp.modules.login.LoginScreen

fun NavGraphBuilder.authNavigation() {
    navigation(
        startDestination = AuthScreens.Login.route,
        route = AuthScreens.AuthNavigation.route
    ) {
        composable(AuthScreens.Login.route) {
            LoginScreen()
        }
        composable(AuthScreens.Register.route) {
//            RegisterScreen()
        }
        composable(AuthScreens.ForgotPassword.route) {
//            ForgotPasswordScreen()
        }
    }
}
