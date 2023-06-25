package com.example.themovieapp.core.composables

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.themovieapp.core.fieldError.ErrorField

@Composable
fun OutlinedEmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelString: String = "Email",
    errorField: ErrorField = ErrorField.Empty
) {
    OutlinedBaseField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        labelString = labelString,
        errorField = errorField,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun OutlinedEmailTextFieldPreview() {
    OutlinedEmailTextField(
        value = "",
        onValueChange = {}
    )
}
