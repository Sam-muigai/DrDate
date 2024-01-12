package com.samkt.drdate.presentation.signUpScreen

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
import com.samkt.drdate.ui.components.DoctorTextField
import com.samkt.drdate.ui.theme.DrDateTheme

@Composable
fun SignUpScreen(navController: NavHostController, signUpViewModel: SignUpViewModel = viewModel()) {
    val signUpScreenState = signUpViewModel.signUpScreenState.collectAsState().value
    SignUpScreenContent(
        signUpScreenState = signUpScreenState,
        onEvent = { event ->
            when (event) {
                is SignUpScreenEvent.OnConfirmPasswordChange -> {
                    signUpViewModel.onConfirmPasswordChange(event.input)
                }

                is SignUpScreenEvent.OnConfirmPasswordEyeClicked -> {
                    signUpViewModel.onConfirmPasswordEyeClicked()
                }

                is SignUpScreenEvent.OnEmailChange -> {
                    signUpViewModel.onEmailChange(event.input)
                }

                is SignUpScreenEvent.OnFirstNameChange -> {
                    signUpViewModel.onFirstNameChange(event.input)
                }

                is SignUpScreenEvent.OnLastNameChange -> {
                    signUpViewModel.onLastNameChange(event.input)
                }

                is SignUpScreenEvent.OnPasswordChange -> {
                    signUpViewModel.onPasswordChange(event.input)
                }

                is SignUpScreenEvent.OnPasswordEyeClicked -> {
                    signUpViewModel.onPasswordEyeClicked()
                }

                is SignUpScreenEvent.OnSignUpClicked -> {
                    // TODO: Sign Up User
                }

                is SignUpScreenEvent.OnBackPressed -> {
                    navController.popBackStack()
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreenContent(
    modifier: Modifier = Modifier,
    signUpScreenState: SignUpScreenState,
    onEvent: (SignUpScreenEvent) -> Unit,
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
                IconButton(onClick = { onEvent(SignUpScreenEvent.OnBackPressed) }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Navigate back",
                    )
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = "Create Account",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )

            Text(
                text = "Start By Creating an Account",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight(500),
                    color = MaterialTheme.colorScheme.onBackground.copy(
                        alpha = 0.8f,
                    ),
                ),
            )
            Spacer(modifier = Modifier.height(56.dp))
            DoctorTextField(
                value = signUpScreenState.firstName,
                placeHolder = "First Name",
                onValueChange = { input ->
                    onEvent(SignUpScreenEvent.OnFirstNameChange(input))
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            DoctorTextField(
                value = signUpScreenState.lastName,
                placeHolder = "Last Name",
                onValueChange = { input ->
                    onEvent(SignUpScreenEvent.OnLastNameChange(input))
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            DoctorTextField(
                value = signUpScreenState.email,
                placeHolder = "Email",
                onValueChange = { input ->
                    onEvent(SignUpScreenEvent.OnEmailChange(input))
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            DoctorPasswordTextField(
                value = signUpScreenState.password,
                placeHolder = "Password",
                onValueChange = { input ->
                    onEvent(SignUpScreenEvent.OnPasswordChange(input))
                },
                onIconButtonClicked = {
                    onEvent(SignUpScreenEvent.OnPasswordEyeClicked)
                },
                isPasswordVisible = signUpScreenState.passwordVisible,
            )
            Spacer(modifier = Modifier.height(16.dp))
            DoctorPasswordTextField(
                value = signUpScreenState.confirmPassword,
                placeHolder = "Confirm Password",
                onValueChange = { input ->
                    onEvent(SignUpScreenEvent.OnConfirmPasswordChange(input))
                },
                onIconButtonClicked = {
                    onEvent(SignUpScreenEvent.OnConfirmPasswordEyeClicked)
                },
                isPasswordVisible = signUpScreenState.confirmPasswordVisible,
            )
            Spacer(modifier = Modifier.height(24.dp))
            DoctorFilledButton(
                text = "Sign Up",
                onClick = {
                    onEvent(SignUpScreenEvent.OnSignUpClicked)
                },
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SignUpScreenPreview() {
    DrDateTheme {
        SignUpScreenContent(
            signUpScreenState = SignUpScreenState(),
            onEvent = {},
        )
    }
}
