package com.example.themovieapp.core.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.themovieapp.core.fieldError.ErrorField

@Composable
fun OutlinedBaseField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    fontSize: Int = 14,
    maxChar: Int = 30,
    shape: RoundedCornerShape = CircleShape,
    labelString: String = "",
    errorField: ErrorField = ErrorField.Empty,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            if (it.length <= maxChar) onValueChange(it)
        },
        modifier = modifier.fillMaxWidth(),
        label = { Text(text = labelString) },
        shape = shape,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.LightGray
        ),
        textStyle = TextStyle.Default.copy(fontSize = fontSize.sp),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        isError = errorField.isError,
        supportingText = {
            if (errorField.isError) {
                Text(text = errorField.message)
            }
        },
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon
    )
}

@Preview
@Composable
private fun OutlinedBaseFieldPreview() {
    OutlinedBaseField(
        value = "",
        onValueChange = {}
    )
}
