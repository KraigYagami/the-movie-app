package com.kraigcorp.themovieapp.modules.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.kraigcorp.themovieapp.R
import kotlinx.coroutines.delay

private const val SPLASH_DELAY = 3000L

@Composable
fun SplashScreen(onNavigate: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(SPLASH_DELAY)
        onNavigate()
    }

    SplashView()
}

@Preview
@Composable
private fun SplashPreview() {
    SplashScreen(onNavigate = {})
}

@Composable
private fun SplashView() {
    Column(
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Splash Image"
        )
        Text(text = "Splash Screen")
    }
}
