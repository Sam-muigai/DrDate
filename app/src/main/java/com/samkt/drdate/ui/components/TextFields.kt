package com.samkt.drdate.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samkt.drdate.R
import com.samkt.drdate.ui.theme.CyanBlue
import com.samkt.drdate.ui.theme.DrDateTheme
import com.samkt.drdate.ui.theme.LightGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeHolder: String,
    onValueChange: (String) -> Unit,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = Color(0xFF1EAFB3),
        unfocusedBorderColor = LightGrey,
    ),
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        shape = MaterialTheme.shapes.extraLarge,
        textStyle = MaterialTheme.typography.bodyMedium,
        placeholder = {
            Text(
                text = placeHolder,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = LightGrey,
                ),
            )
        },
        colors = colors,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorPasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeHolder: String,
    onValueChange: (String) -> Unit,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = CyanBlue,
        unfocusedBorderColor = LightGrey,
        focusedTrailingIconColor = CyanBlue,
        unfocusedTrailingIconColor = LightGrey,
    ),
    onIconButtonClicked: () -> Unit,
    isPasswordVisible: Boolean = false,
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        shape = MaterialTheme.shapes.extraLarge,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        textStyle = MaterialTheme.typography.bodyMedium,
        placeholder = {
            Text(
                text = placeHolder,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = LightGrey,
                ),
            )
        },
        colors = colors,
        trailingIcon = {
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = onIconButtonClicked,
            ) {
                Icon(
                    painter = painterResource(id = if (isPasswordVisible) R.drawable.eye_closed else R.drawable.eye_open),
                    contentDescription = null,
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun DoctorTextFieldPreview() {
    DrDateTheme {
        DoctorTextField(
            modifier = Modifier.padding(horizontal = 26.dp),
            value = "",
            placeHolder = "Enter Email",
            onValueChange = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DoctorPasswordTextFieldPreview() {
    DrDateTheme {
        DoctorPasswordTextField(
            modifier = Modifier.padding(horizontal = 26.dp),
            value = "",
            placeHolder = "Enter Password",
            onValueChange = {},
            isPasswordVisible = true,
            onIconButtonClicked = {},
        )
    }
}
