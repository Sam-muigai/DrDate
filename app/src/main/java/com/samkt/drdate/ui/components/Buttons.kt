package com.samkt.drdate.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samkt.drdate.ui.theme.CyanBlue
import com.samkt.drdate.ui.theme.DrDateTheme

@Composable
fun DoctorFilledButton(
    modifier: Modifier = Modifier,
    text: String = "Login",
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = CyanBlue,
        contentColor = Color.White,
    ),
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        colors = colors,
        shape = MaterialTheme.shapes.large,
    ) {
        Text(
            modifier = Modifier.padding(vertical = 2.dp),
            text = text,
            style = MaterialTheme.typography.titleLarge.copy(
                lineHeight = 14.sp,
                fontWeight = FontWeight(500),
            ),
        )
    }
}

@Composable
fun DoctorOutlinedButton(
    modifier: Modifier = Modifier,
    text: String = "Register",
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(
        contentColor = CyanBlue,
    ),
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        colors = colors,
        border = BorderStroke(1.dp, CyanBlue),
        shape = MaterialTheme.shapes.large,
    ) {
        Text(
            modifier = Modifier.padding(vertical = 2.dp),
            text = text,
            style = MaterialTheme.typography.titleLarge.copy(
                lineHeight = 14.sp,
                fontWeight = FontWeight(500),
            ),
        )
    }
}

@Composable
fun DoctorTextButton(
    modifier: Modifier = Modifier,
    text: String = "Forgot Password?",
    colors: ButtonColors = ButtonDefaults.textButtonColors(
        contentColor = CyanBlue,
    ),
    onClick: () -> Unit,
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        colors = colors,
        shape = MaterialTheme.shapes.large,
    ) {
        Text(
            modifier = Modifier.padding(vertical = 2.dp).drawBehind {
                val strokeWidthPx = 2.dp.toPx()
                val verticalOffset = size.height - 2.sp.toPx()
                drawLine(
                    color = CyanBlue,
                    strokeWidth = strokeWidthPx,
                    start = Offset(0f, verticalOffset),
                    end = Offset(size.width, verticalOffset),
                )
            },
            text = text,
            style = MaterialTheme.typography.titleSmall.copy(
                lineHeight = 14.sp,
                fontWeight = FontWeight(500),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DoctorOutlinedButtonPreview() {
    DrDateTheme {
        DoctorOutlinedButton(
            modifier = Modifier.padding(24.dp),
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DoctorFilledButtonPreview() {
    DrDateTheme {
        DoctorFilledButton(
            modifier = Modifier.padding(24.dp),
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DoctorTextButtonPreview() {
    DrDateTheme {
        DoctorTextButton(
            modifier = Modifier.padding(24.dp),
            onClick = {},
        )
    }
}
