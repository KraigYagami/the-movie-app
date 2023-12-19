package com.kraigcorp.themovieapp.core.auth

import android.util.Log
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class GoogleEmailAuthClient {

    companion object {
        private const val TAG = "GoogleEmailAuthClient"
    }

    private val auth = Firebase.auth

    suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): AuthResult {
        return auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    user?.sendEmailVerification()?.addOnCompleteListener { sendEmailTask ->
                        if (sendEmailTask.isSuccessful) {
                            Log.d("AuthNavigation", "Email sent.")
                        }
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Log.e(TAG, "createUserWithEmail:failure", task.exception)
                }
            }.await()
    }

    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): AuthResult {
        return auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithEmail:success")
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithEmail:failure", task.exception)
            }
        }.await()
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    fun resendVerificationEmail(emailSent: () -> Unit) {
        val user = auth.currentUser
        user?.sendEmailVerification()?.addOnCompleteListener { sendEmailTask ->
            if (sendEmailTask.isSuccessful) {
                Log.d("AuthNavigation", "Email sent.")
                emailSent()
            }
        }
    }
}
