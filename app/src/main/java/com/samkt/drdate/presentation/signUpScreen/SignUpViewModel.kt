package com.samkt.drdate.presentation.signUpScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel : ViewModel() {
    private val _signUpScreenState = MutableStateFlow(SignUpScreenState())
    val signUpScreenState = _signUpScreenState.asStateFlow()

    fun onEmailChange(input: String) {
        _signUpScreenState.update {
            it.copy(
                email = input,
            )
        }
    }

    fun onFirstNameChange(input: String) {
        _signUpScreenState.update {
            it.copy(
                firstName = input,
            )
        }
    }

    fun onLastNameChange(input: String) {
        _signUpScreenState.update {
            it.copy(
                lastName = input,
            )
        }
    }

    fun onPasswordChange(input: String) {
        _signUpScreenState.update {
            it.copy(
                password = input,
            )
        }
    }

    fun onConfirmPasswordChange(input: String) {
        _signUpScreenState.update {
            it.copy(
                confirmPassword = input,
            )
        }
    }

    fun onPasswordEyeClicked() {
        _signUpScreenState.update {
            it.copy(
                passwordVisible = !signUpScreenState.value.passwordVisible,
            )
        }
    }

    fun onConfirmPasswordEyeClicked() {
        _signUpScreenState.update {
            it.copy(
                confirmPasswordVisible = !signUpScreenState.value.confirmPasswordVisible,
            )
        }
    }
}

data class SignUpScreenState(
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val passwordVisible: Boolean = false,
    val confirmPasswordVisible: Boolean = false,
)

sealed class SignUpScreenEvent {
    data class OnEmailChange(val input: String) : SignUpScreenEvent()
    data class OnFirstNameChange(val input: String) : SignUpScreenEvent()
    data class OnLastNameChange(val input: String) : SignUpScreenEvent()
    data class OnPasswordChange(val input: String) : SignUpScreenEvent()
    data class OnConfirmPasswordChange(val input: String) : SignUpScreenEvent()
    object OnSignUpClicked : SignUpScreenEvent()
    object OnPasswordEyeClicked : SignUpScreenEvent()
    object OnConfirmPasswordEyeClicked : SignUpScreenEvent()
    object OnBackPressed : SignUpScreenEvent()
}
