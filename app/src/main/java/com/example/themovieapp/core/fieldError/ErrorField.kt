package com.example.themovieapp.core.fieldError

data class ErrorField(val isError: Boolean, val message: String) {
    companion object {
        val Empty = ErrorField(false, "")
    }
}
