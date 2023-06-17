package com.example.themovieapp.modules.splash

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SplashScreen(onNavigate: () -> Unit) {
    SplashView()
}

@Preview
@Composable
private fun SplashPreview() {
    SplashScreen(onNavigate = {})
}

@Composable
private fun SplashView() {
    Text(text = "Splash Screen")
}
