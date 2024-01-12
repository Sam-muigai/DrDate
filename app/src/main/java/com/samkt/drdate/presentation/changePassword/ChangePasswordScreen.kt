package com.samkt.drdate.presentation.changePassword

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
import com.samkt.drdate.ui.components.DoctorPasswordTextField
import com.samkt.drdate.ui.theme.DrDateTheme

@Composable
fun ChangePasswordScreen(
    navController: NavHostController,
    changePasswordViewModel: ChangePasswordViewModel = viewModel(),
) {
    val changePasswordState = changePasswordViewModel.changePasswordState.collectAsState().value
    ChangePasswordScreenContent(
        changePasswordState = changePasswordState,
        onEvent = { events ->
            when (events) {
                is ChangedPasswordEvents.OnBackPressed -> {
                    navController.popBackStack()
                }

                is ChangedPasswordEvents.OnConfirmPasswordChange -> {
                    changePasswordViewModel.onConfirmPasswordChange(events.input)
                }

                is ChangedPasswordEvents.OnConfirmPasswordEyeClicked -> {
                    changePasswordViewModel.onConfirmPasswordEyeClicked()
                }

                is ChangedPasswordEvents.OnPasswordChange -> {
                    changePasswordViewModel.onPasswordChange(events.input)
                }

                is ChangedPasswordEvents.OnPasswordEyeClicked -> {
                    changePasswordViewModel.onPasswordEyeClicked()
                }

                is ChangedPasswordEvents.OnSignUpClicked -> {
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreenContent(
    modifier: Modifier = Modifier,
    changePasswordState: ChangePasswordState,
    onEvent: (ChangedPasswordEvents) -> Unit,
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
                    onEvent(ChangedPasswordEvents.OnBackPressed)
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Navigate back",
                    )
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = "Create New Password",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )

            Text(
                text = "Enter your new password",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight(500),
                    color = MaterialTheme.colorScheme.onBackground.copy(
                        alpha = 0.8f,
                    ),
                ),
            )
            Spacer(modifier = Modifier.height(36.dp))
            DoctorPasswordTextField(
                value = changePasswordState.password,
                placeHolder = "New Password",
                onValueChange = { input ->
                    onEvent(ChangedPasswordEvents.OnPasswordChange(input))
                },
                onIconButtonClicked = {
                    onEvent(ChangedPasswordEvents.OnPasswordEyeClicked)
                },
                isPasswordVisible = changePasswordState.passwordVisible,
            )
            Spacer(modifier = Modifier.height(16.dp))
            DoctorPasswordTextField(
                value = changePasswordState.confirmPassword,
                placeHolder = "Confirm Password",
                onValueChange = { input ->
                    onEvent(ChangedPasswordEvents.OnConfirmPasswordChange(input))
                },
                onIconButtonClicked = {
                    onEvent(ChangedPasswordEvents.OnConfirmPasswordEyeClicked)
                },
                isPasswordVisible = changePasswordState.confirmPasswordVisible,
            )
            Spacer(modifier = Modifier.height(16.dp))
            DoctorFilledButton(
                text = "Sign Up",
                onClick = {
                    onEvent(ChangedPasswordEvents.OnSignUpClicked)
                },
            )
        }
    }
}

@Preview
@Composable
fun ChangePasswordScreenPreview() {
    DrDateTheme {
        ChangePasswordScreenContent(
            changePasswordState = ChangePasswordState(),
            onEvent = {},
        )
    }
}
