package com.example.themovieapp.modules.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.themovieapp.core.auth.SignInViewModel

@Composable
fun LoginScreen(
    state: SignInViewModel.SignInState,
    onSignIn: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(state.signInError) {
        state.signInError?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    LoginView(onSignIn = onSignIn)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginPreview() {
    LoginScreen(
        state = SignInViewModel.SignInState(),
        onSignIn = {}
    )
}

@Composable
private fun LoginView(onSignIn: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = onSignIn) {
            Text(text = "Sign in with Google")
        }
    }
}
