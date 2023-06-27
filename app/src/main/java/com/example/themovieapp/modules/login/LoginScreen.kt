package com.example.themovieapp.modules.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.themovieapp.R
import com.example.themovieapp.core.auth.SignInViewModel
import com.example.themovieapp.core.composables.GoogleButton
import com.example.themovieapp.core.composables.OutlinedEmailTextField
import com.example.themovieapp.core.composables.OutlinedPasswordField

@Composable
fun LoginScreen(
    state: SignInViewModel.SignInState,
    onLoginUser: (email: String, password: String) -> Unit,
    onCreateUser: (email: String, password: String) -> Unit,
    onGoogleSignInButton: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(state.signInError) {
        state.signInError?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    LoginView(
        onLoginUser = onLoginUser,
        onCreateUser = onCreateUser,
        onGoogleSignInButton = onGoogleSignInButton
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginPreview() {
    LoginScreen(
        state = SignInViewModel.SignInState(),
        onLoginUser = { _, _ -> },
        onCreateUser = { _, _ -> },
        onGoogleSignInButton = {}
    )
}

@Composable
private fun LoginView(
    onLoginUser: (email: String, password: String) -> Unit,
    onCreateUser: (email: String, password: String) -> Unit,
    onGoogleSignInButton: () -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
    ) {
        val (header, logo, content, register) = createRefs()

        Box(
            modifier = Modifier
                .fillMaxHeight(.3f)
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                .background(MaterialTheme.colorScheme.secondary)
                .constrainAs(header) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        )
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Logo",
            modifier = Modifier.padding(bottom = 16.dp).constrainAs(logo) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(header.bottom)
            }
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(content) {
                    top.linkTo(logo.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(register.top)
                }
        ) {
            OutlinedEmailTextField(value = email, onValueChange = { email = it })
            OutlinedPasswordField(value = password, onValueChange = { password = it })
            TextButton(
                onClick = {},
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Forgot password?")
            }
            Button(onClick = {
                onLoginUser(email, password)
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Login", fontSize = 16.sp)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(modifier = Modifier.weight(1f))
                Text(text = "Or login with", modifier = Modifier.padding(horizontal = 8.dp))
                Divider(modifier = Modifier.weight(1f))
            }

            GoogleButton(
                onClicked = onGoogleSignInButton,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.extraLarge
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(register) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Don't have an account?")
            TextButton(onClick = {
                onCreateUser(email, password)
            }) {
                Text(text = "Register")
            }
        }
    }
}
