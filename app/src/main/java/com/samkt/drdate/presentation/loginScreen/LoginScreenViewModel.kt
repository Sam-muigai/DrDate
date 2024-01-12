package com.samkt.drdate.presentation.loginScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginScreenViewModel : ViewModel() {
    private val _loginScreenState = MutableStateFlow(LoginScreenState())
    val loginScreenState = _loginScreenState.asStateFlow()

    fun onEmailChange(input: String) {
        _loginScreenState.update {
            it.copy(
                email = input,
            )
        }
    }

    fun onPasswordChange(input: String) {
        _loginScreenState.update {
            it.copy(
                password = input,
            )
        }
    }

    fun onEyeIconToggle() {
        _loginScreenState.update {
            it.copy(
                passwordVisible = !loginScreenState.value.passwordVisible,
            )
        }
    }
}

data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
)

sealed class LoginScreenEvents() {
    data class OnEmailChange(val input: String) : LoginScreenEvents()
    data class OnPasswordChange(val input: String) : LoginScreenEvents()
    object OnLoginClicked : LoginScreenEvents()
    object OnRegisterClicked : LoginScreenEvents()
    object OnEyeIconClicked : LoginScreenEvents()
    object OnForgotPasswordClicked : LoginScreenEvents()
}
