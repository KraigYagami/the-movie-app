package com.example.themovieapp.core.navigation.routes.auth

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.themovieapp.core.auth.GoogleAuthUiClient
import com.example.themovieapp.core.auth.SignInViewModel
import com.example.themovieapp.core.navigation.AppScreens
import com.example.themovieapp.modules.login.LoginScreen
import kotlinx.coroutines.launch

fun NavGraphBuilder.authNavigation(
    navController: NavController,
    googleAuthUiClient: GoogleAuthUiClient
) {
    navigation(
        startDestination = AuthScreens.Login.route,
        route = AppScreens.AuthNavigation.route
    ) {
        composable(AuthScreens.Login.route) {
            val viewModel = hiltViewModel<SignInViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val scope = rememberCoroutineScope()
            val context = LocalContext.current

            LaunchedEffect(Unit) {
                if (googleAuthUiClient.getSignInUser() != null) {
                    navController.navigate(AppScreens.HomeNavigation.route)
                }
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == RESULT_OK) {
                        scope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                result.data ?: return@launch
                            )

                            viewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {
                    Toast.makeText(context, "Sign in successful", Toast.LENGTH_LONG).show()

                    navController.navigate(AppScreens.HomeNavigation.route)
                    viewModel.resetState()
                }
            }

            LoginScreen(
                state = state,
                onSignIn = {
                    scope.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                }
            )
        }
        composable(AuthScreens.Register.route) {
//            RegisterScreen()
        }
        composable(AuthScreens.ForgotPassword.route) {
//            ForgotPasswordScreen()
        }
    }
}
