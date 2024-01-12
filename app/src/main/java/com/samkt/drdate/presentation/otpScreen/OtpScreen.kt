package com.samkt.drdate.presentation.otpScreen

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.samkt.drdate.ui.components.DoctorFilledButton
import com.samkt.drdate.ui.theme.CyanBlue
import com.samkt.drdate.ui.theme.DrDateTheme
import com.samkt.drdate.ui.theme.LightGrey
import com.samkt.drdate.utils.Constants.CHANGE_PASSWORD_ROUTE

@Composable
fun OtpScreen(
    navController: NavHostController,
    otpScreenViewModel: OtpScreenViewModel = viewModel(),
) {
    val otpScreenState = otpScreenViewModel.otpScreenState.collectAsState().value
    OtpScreenContent(
        otpScreenState = otpScreenState,
        onEvent = { event ->
            when (event) {
                is OtpScreenEvent.OnBackPressed -> {
                    navController.popBackStack()
                }

                is OtpScreenEvent.OnCodeChange -> {
                    otpScreenViewModel.onCodeChange(event.input)
                }

                is OtpScreenEvent.OnVerifyClicked -> {
                    navController.navigate(CHANGE_PASSWORD_ROUTE)
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpScreenContent(
    modifier: Modifier = Modifier,
    otpScreenState: OtpScreenState,
    onEvent: (OtpScreenEvent) -> Unit,
) {
    Scaffold(
        modifier = modifier,
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                IconButton(onClick = {
                    onEvent(OtpScreenEvent.OnBackPressed)
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Navigate back",
                    )
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = "Forgot Password?",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )

            Text(
                text = "Don't worry! It occurs. Please enter the email address linked with your account.",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight(500),
                    color = MaterialTheme.colorScheme.onBackground.copy(
                        alpha = 0.8f,
                    ),
                ),
            )
            Spacer(modifier = Modifier.height(36.dp))
            OtpInputField(
                codeLength = 4,
                initialCode = otpScreenState.code,
                onTextChanged = {
                    onEvent(OtpScreenEvent.OnCodeChange(it))
                },
            )
            Spacer(modifier = Modifier.height(24.dp))
            DoctorFilledButton(
                text = "Verify",
                onClick = {
                    onEvent(OtpScreenEvent.OnVerifyClicked)
                },
            )
        }
    }
}

@Composable
fun OtpInputField(
    codeLength: Int,
    initialCode: String,
    onTextChanged: (String) -> Unit,
) {
    val code = remember(initialCode) {
        mutableStateOf(TextFieldValue(initialCode, TextRange(initialCode.length)))
    }
    val focusRequester = FocusRequester()
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        BasicTextField(
            value = code.value,
            onValueChange = { onTextChanged(it.text) },
            modifier = Modifier.focusRequester(focusRequester = focusRequester),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            decorationBox = {
                OtpInputDecoration(code.value.text, codeLength)
            },
        )
    }
}

@Composable
private fun OtpInputDecoration(
    code: String,
    length: Int,
) {
    Box(modifier = Modifier) {
        Row(horizontalArrangement = Arrangement.Center) {
            for (i in 0 until length) {
                val text = if (i < code.length) code[i].toString() else ""
                OtpInput(text)
            }
        }
    }
}

@Composable
private fun OtpInput(text: String) {
    val color = animateColorAsState(
        targetValue = if (text.isEmpty()) {
            Color.Gray.copy(alpha = .8f)
        } else {
            CyanBlue
        },
        label = "",
    )
    Box(
        modifier = Modifier
            .padding(4.dp)
            .width(72.dp)
            .height(56.dp)
            .border(
                width = 1.dp,
                color = if (text.isEmpty()) {
                    LightGrey
                } else {
                    color.value
                },
                shape = MaterialTheme.shapes.large,
            )
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = MaterialTheme.shapes.large,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun OtpScreenPreview() {
    DrDateTheme {
        OtpScreenContent(
            otpScreenState = OtpScreenState(),
            onEvent = {},
        )
    }
}
