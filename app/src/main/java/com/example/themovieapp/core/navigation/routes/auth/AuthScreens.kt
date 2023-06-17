package com.example.themovieapp.core.navigation.routes.auth

sealed class AuthScreens(val route: String) {
    object Login : AuthScreens("login")
    object Register : AuthScreens("register")
    object ForgotPassword : AuthScreens("forgot_password")
}
