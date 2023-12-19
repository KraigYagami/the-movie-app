package com.kraigcorp.themovieapp.core.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    fun onSignUpResult(result: AuthResult) {
        _state.update {
            it.copy(
                isSignUpSuccessful = result.user != null,
                signUpError = if (result.user == null) "Sign up failed" else null
            )
        }
    }

    fun resetState() {
        _state.update { SignUpState() }
    }

    data class SignUpState(
        val isSignUpSuccessful: Boolean = false,
        val signUpError: String? = null
    )
}
