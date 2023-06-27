package com.example.themovieapp.core.navigation.routes.auth

sealed class AuthScreens(val route: String) {
    object Login : AuthScreens("login")
    object Register : AuthScreens("register")
    object EmailVerification : AuthScreens("email_verification")
    object ForgotPassword : AuthScreens("forgot_password")
}
