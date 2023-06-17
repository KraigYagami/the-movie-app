package com.example.themovieapp.modules.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen() {
    HomeScreenView()
}

@Preview
@Composable
private fun HomePreview() {
    HomeScreen()
}

@Composable
private fun HomeScreenView() {
    Text(text = "Home Screen")
}
