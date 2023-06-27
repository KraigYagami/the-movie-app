package com.example.themovieapp.core.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInWithEmailResult(result: AuthResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = result.user != null,
                signInError = if (result.user == null) "Sign in failed" else null
            )
        }
    }

    fun onSignInWithGoogleResult(result: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    fun resetState() {
        _state.update { SignInState() }
    }

    data class SignInState(
        val isSignInSuccessful: Boolean = false,
        val signInError: String? = null
    )
}
