package com.samkt.drdate.presentation.loginScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.samkt.drdate.R
import com.samkt.drdate.ui.components.DoctorFilledButton
import com.samkt.drdate.ui.components.DoctorOutlinedButton
import com.samkt.drdate.ui.components.DoctorPasswordTextField
import com.samkt.drdate.ui.components.DoctorTextButton
import com.samkt.drdate.ui.components.DoctorTextField
import com.samkt.drdate.ui.theme.CyanBlue
import com.samkt.drdate.ui.theme.DrDateTheme
import com.samkt.drdate.ui.theme.spicyFamily
import com.samkt.drdate.utils.Constants.FORGOT_PASSWORD_ROUTE
import com.samkt.drdate.utils.Constants.SIGN_UP_ROUTE

@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginScreenViewModel = viewModel(),
) {
    val loginScreenState = loginViewModel.loginScreenState.collectAsState().value

    LoginScreenContent(
        loginScreenState = loginScreenState,
        onEvent = { event ->
            when (event) {
                is LoginScreenEvents.OnEmailChange -> {
                    loginViewModel.onEmailChange(event.input)
                }

                is LoginScreenEvents.OnEyeIconClicked -> {
                    loginViewModel.onEyeIconToggle()
                }

                is LoginScreenEvents.OnForgotPasswordClicked -> {
                    navController.navigate(FORGOT_PASSWORD_ROUTE)
                }

                is LoginScreenEvents.OnLoginClicked -> {
                    // TODO: Login user
                }

                is LoginScreenEvents.OnPasswordChange -> {
                    loginViewModel.onPasswordChange(event.input)
                }

                is LoginScreenEvents.OnRegisterClicked -> {
                    navController.navigate(SIGN_UP_ROUTE)
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    loginScreenState: LoginScreenState,
    onEvent: (LoginScreenEvents) -> Unit,
) {
    Scaffold(
        modifier = modifier,
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxWidth()
                .verticalScroll(rememberScrollState()).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Image(
                painter = painterResource(id = R.drawable.heart_logo),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = CyanBlue)) {
                        append("Dr.")
                    }
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append("Date")
                    }
                },
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontFamily = spicyFamily,
                ),
            )
            Spacer(modifier = Modifier.height(56.dp))
            DoctorTextField(
                value = loginScreenState.email,
                placeHolder = "Enter Email",
                onValueChange = { input ->
                    onEvent(LoginScreenEvents.OnEmailChange(input))
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            DoctorPasswordTextField(
                value = loginScreenState.password,
                placeHolder = "Enter Password",
                onValueChange = { input ->
                    onEvent(LoginScreenEvents.OnPasswordChange(input))
                },
                onIconButtonClicked = {
                    onEvent(LoginScreenEvents.OnEyeIconClicked)
                },
                isPasswordVisible = loginScreenState.passwordVisible,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                DoctorTextButton(
                    text = "Forgot Password?",
                    onClick = {
                        onEvent(LoginScreenEvents.OnForgotPasswordClicked)
                    },
                )
            }
            Spacer(modifier = Modifier.height(64.dp))
            DoctorFilledButton(
                text = "Login",
                onClick = {
                    onEvent(LoginScreenEvents.OnLoginClicked)
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            DoctorOutlinedButton(
                onClick = {
                    onEvent(LoginScreenEvents.OnRegisterClicked)
                },
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    DrDateTheme {
        LoginScreenContent(
            loginScreenState = LoginScreenState(),
            onEvent = {},
        )
    }
}
