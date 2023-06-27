package com.example.themovieapp.core.navigation.routes.home

import android.widget.Toast
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.themovieapp.core.auth.GoogleAuthUiClient
import com.example.themovieapp.core.navigation.AppScreens
import com.example.themovieapp.modules.home.HomeScreen
import kotlinx.coroutines.launch

fun NavGraphBuilder.homeNavigation(
    navController: NavController,
    googleAuthUiClient: GoogleAuthUiClient
) {
    navigation(
        startDestination = HomeScreens.Home.route,
        route = AppScreens.HomeNavigation.route
    ) {
        composable(HomeScreens.Home.route) {
            val scope = rememberCoroutineScope()
            val context = LocalContext.current

            HomeScreen(
                userData = googleAuthUiClient.getSignInUser(),
                onSignOut = {
                    scope.launch {
                        googleAuthUiClient.signOut()
                        Toast.makeText(context, "Signed out", Toast.LENGTH_LONG).show()
                        navController.popBackStack()
                        navController.navigate(AppScreens.AuthNavigation.route)
                    }
                }
            )
        }
        composable(HomeScreens.MovieDetail.route) {
//            MovieDetailScreen()
        }
    }
}
