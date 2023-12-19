package com.kraigcorp.themovieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.android.gms.auth.api.identity.Identity
import com.kraigcorp.themovieapp.core.auth.GoogleAuthUiClient
import com.kraigcorp.themovieapp.core.auth.GoogleEmailAuthClient
import com.kraigcorp.themovieapp.core.navigation.AppNavigation
import com.kraigcorp.themovieapp.ui.theme.TheMovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    private val googleEmailAuthClient by lazy {
        GoogleEmailAuthClient()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheMovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(
                        googleAuthUiClient = googleAuthUiClient,
                        googleEmailAuthClient = googleEmailAuthClient
                    )
                }
            }
        }
    }
}
