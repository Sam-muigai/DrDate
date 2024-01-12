package com.samkt.drdate.presentation.forgotPassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.samkt.drdate.ui.components.DoctorFilledButton
import com.samkt.drdate.ui.components.DoctorTextField
import com.samkt.drdate.ui.theme.DrDateTheme
import com.samkt.drdate.utils.Constants.OTP_ROUTE

@Composable
fun ForgotPasswordScreen(
    navController: NavHostController,
    forgotViewModel: ForgotPasswordViewModel = viewModel(),
) {
    val forgotPasswordState = forgotViewModel.forgotPasswordState.collectAsState().value
    ForgotPasswordScreenContent(
        forgotPasswordState = forgotPasswordState,
        onEvent = { event ->
            when (event) {
                is ForgotPasswordEvents.OnBackPressed -> {
                    navController.popBackStack()
                }

                is ForgotPasswordEvents.OnEmailChange -> {
                    forgotViewModel.onEmailChange(event.input)
                }

                is ForgotPasswordEvents.OnSendCode -> {
                    navController.navigate(OTP_ROUTE)
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreenContent(
    modifier: Modifier = Modifier,
    forgotPasswordState: ForgotPasswordState,
    onEvent: (ForgotPasswordEvents) -> Unit,
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
                    onEvent(ForgotPasswordEvents.OnBackPressed)
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
            DoctorTextField(
                value = forgotPasswordState.email,
                placeHolder = "Email",
                onValueChange = { input ->
                    onEvent(ForgotPasswordEvents.OnEmailChange(input))
                },
            )
            Spacer(modifier = Modifier.height(24.dp))
            DoctorFilledButton(
                text = "Send Code",
                onClick = {
                    onEvent(ForgotPasswordEvents.OnSendCode)
                },
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ForgotPasswordScreenPreview() {
    DrDateTheme {
        ForgotPasswordScreenContent(
            forgotPasswordState = ForgotPasswordState(),
            onEvent = {},
        )
    }
}
