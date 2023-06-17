package com.example.themovieapp.modules.login

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreen() {
    LoginView()
}

@Preview
@Composable
private fun LoginPreview() {
    LoginScreen()
}

@Composable
private fun LoginView() {
    Text(text = "Login Screen")
}
