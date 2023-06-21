package com.example.themovieapp.modules.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.themovieapp.core.auth.SignInResult

@Composable
fun HomeScreen(
    userData: SignInResult.UserData?,
    onSignOut: () -> Unit
) {
    HomeView(userData = userData, onSignOut = onSignOut)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomePreview() {
    HomeScreen(
        userData = SignInResult.UserData(
            userId = "123",
            userName = "John Doe",
            photoUrl = "https://example.com/photo.jpg"
        ),
        onSignOut = {}
    )
}

@Composable
private fun HomeView(
    userData: SignInResult.UserData?,
    onSignOut: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        userData?.photoUrl?.let {
            AsyncImage(
                model = it,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        userData?.userName?.let {
            Text(
                text = "Hello, $it",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Button(onClick = onSignOut) {
            Text(text = "Sign out")
        }
    }
}
