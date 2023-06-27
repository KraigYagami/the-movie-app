package com.example.themovieapp.core.navigation.routes.auth

import android.app.Activity.RESULT_OK
import android.util.Log
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
import com.example.themovieapp.core.auth.GoogleEmailAuthClient
import com.example.themovieapp.core.auth.SignInViewModel
import com.example.themovieapp.core.auth.SignUpViewModel
import com.example.themovieapp.core.navigation.AppScreens
import com.example.themovieapp.modules.emailVerification.EmailVerificationScreen
import com.example.themovieapp.modules.login.LoginScreen
import com.example.themovieapp.modules.register.RegisterScreen
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.launch

fun NavGraphBuilder.authNavigation(
    navController: NavController,
    googleAuthUiClient: GoogleAuthUiClient,
    googleEmailAuthClient: GoogleEmailAuthClient
) {
    navigation(
        startDestination = AuthScreens.Login.route,
        route = AppScreens.AuthNavigation.route
    ) {
        composable(AuthScreens.Login.route) {
            val signInViewModel = hiltViewModel<SignInViewModel>()
            val signInState by signInViewModel.state.collectAsStateWithLifecycle()
            val signUpViewModel = hiltViewModel<SignUpViewModel>()
            val signUpState by signUpViewModel.state.collectAsStateWithLifecycle()
            val scope = rememberCoroutineScope()
            val context = LocalContext.current

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == RESULT_OK) {
                        scope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                result.data ?: return@launch
                            )

                            signInViewModel.onSignInWithGoogleResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(Unit) {
                if (googleAuthUiClient.getSignInUser() != null) {
                    navController.popBackStack()
                    navController.navigate(AppScreens.HomeNavigation.route)
                }
            }

            LaunchedEffect(signInState.isSignInSuccessful) {
                if (signInState.isSignInSuccessful) {
                    Toast.makeText(context, "Sign in successful", Toast.LENGTH_LONG).show()

                    navController.popBackStack()
                    navController.navigate(AppScreens.HomeNavigation.route)
                    signInViewModel.resetState()
                }
            }

            LaunchedEffect(signUpState.isSignUpSuccessful) {
                if (signUpState.isSignUpSuccessful) {
                    Toast.makeText(context, "Sign up successful", Toast.LENGTH_LONG).show()

                    val user = googleEmailAuthClient.getCurrentUser()
                    if (user != null) {
                        Log.d("AuthNavigation", "User: ${user.isEmailVerified}")
                        if (user.isEmailVerified) {
                            // Validate user data
                        } else {
                            navController.navigate(AuthScreens.EmailVerification.route)
                            signUpViewModel.resetState()
                        }
                    }
                }
            }

            LoginScreen(
                state = signInState,
                onLoginUser = { email, password ->
                    scope.launch {
                        val signInResult = googleEmailAuthClient.signInWithEmailAndPassword(
                            email = email,
                            password = password
                        )

                        signInViewModel.onSignInWithEmailResult(signInResult)
                    }
                },
                onCreateUser = { email, password ->
                    scope.launch {
                        val signUpResult = googleEmailAuthClient.createUserWithEmailAndPassword(
                            email = email,
                            password = password
                        )

                        signUpViewModel.onSignUpResult(signUpResult)
                    }
                },
                onGoogleSignInButton = {
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
        composable(AuthScreens.EmailVerification.route) {
            val user = googleEmailAuthClient.getCurrentUser()

            val context = LocalContext.current
            EmailVerificationScreen(
                onSendEmailVerification = {
                    googleEmailAuthClient.resendVerificationEmail {
                        Toast.makeText(context, "Email sent", Toast.LENGTH_SHORT).show()
                    }
                },
                onVerifyEmail = {
                    user?.let {
                        it.reload().addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                if (it.isEmailVerified) {
                                    navController.navigate(AuthScreens.Register.route)
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Verify Email",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                }
            )
        }
        composable(AuthScreens.Register.route) {
            val context = LocalContext.current
            RegisterScreen(
                onNameChange = { name ->
                    val profileUpdates = userProfileChangeRequest {
                        displayName = name
                    }
                    val user = googleEmailAuthClient.getCurrentUser()
                    user?.let {
                        it.updateProfile(profileUpdates).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "User profile updated",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            navController.popBackStack()
                            navController.navigate(AppScreens.HomeNavigation.route)
                        }
                    }
                }
            )
        }
        composable(AuthScreens.ForgotPassword.route) {
//            ForgotPasswordScreen()
        }
    }
}
