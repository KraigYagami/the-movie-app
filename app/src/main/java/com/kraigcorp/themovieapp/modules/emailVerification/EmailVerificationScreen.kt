package com.kraigcorp.themovieapp.modules.emailVerification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EmailVerificationScreen(onSendEmailVerification: () -> Unit, onVerifyEmail: () -> Unit) {
    EmailVerificationView(
        onSendEmailVerification = onSendEmailVerification,
        onVerifyEmail = onVerifyEmail
    )
}

@Preview
@Composable
private fun EmailVerificationPreview() {
    EmailVerificationScreen(
        onSendEmailVerification = {},
        onVerifyEmail = {}
    )
}

@Composable
private fun EmailVerificationView(onSendEmailVerification: () -> Unit, onVerifyEmail: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Email Verification Screen")
        Text(text = "Resend Email Verification")
        Button(onClick = onSendEmailVerification) {
            Text(text = "Resend")
        }
        Button(onClick = onVerifyEmail) {
            Text(text = "Verify Email")
        }
    }
}
