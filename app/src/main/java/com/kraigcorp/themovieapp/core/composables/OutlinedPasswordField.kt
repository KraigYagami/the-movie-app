package com.kraigcorp.themovieapp.core.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kraigcorp.themovieapp.R
import com.kraigcorp.themovieapp.core.fieldError.ErrorField

@Composable
fun OutlinedPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelString: String = "Password",
    errorField: ErrorField = ErrorField.Empty
) {
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }

    OutlinedBaseField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        labelString = labelString,
        errorField = errorField,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            IconButton(
                onClick = { passwordVisibility = !passwordVisibility },
                modifier = Modifier.padding(end = 4.dp)
            ) {
                Icon(
                    painter = painterResource(
                        id = if (passwordVisibility) {
                            R.drawable.ic_visibility_off
                        } else {
                            R.drawable.ic_visibility
                        }
                    ),
                    contentDescription = "Icon visibility",
                    tint = Color.LightGray
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordFieldPreview() {
    OutlinedPasswordField(
        value = "",
        onValueChange = {}
    )
}
