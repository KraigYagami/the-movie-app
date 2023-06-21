package com.example.themovieapp.core.auth

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
) {
    data class UserData(
        val userId: String,
        val userName: String?,
        val photoUrl: String?
    )
}
