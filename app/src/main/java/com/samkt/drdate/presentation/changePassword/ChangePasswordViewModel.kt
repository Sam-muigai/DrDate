package com.samkt.drdate.presentation.changePassword

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ChangePasswordViewModel : ViewModel() {
    private val _changePasswordState = MutableStateFlow(ChangePasswordState())
    val changePasswordState = _changePasswordState.asStateFlow()

    fun onPasswordChange(input: String) {
        _changePasswordState.update {
            it.copy(
                password = input,
            )
        }
    }

    fun onConfirmPasswordChange(input: String) {
        _changePasswordState.update {
            it.copy(
                confirmPassword = input,
            )
        }
    }

    fun onPasswordEyeClicked() {
        _changePasswordState.update {
            it.copy(
                passwordVisible = !changePasswordState.value.passwordVisible,
            )
        }
    }

    fun onConfirmPasswordEyeClicked() {
        _changePasswordState.update {
            it.copy(
                confirmPasswordVisible = !changePasswordState.value.confirmPasswordVisible,
            )
        }
    }
}

data class ChangePasswordState(
    val password: String = "",
    val confirmPassword: String = "",
    val passwordVisible: Boolean = false,
    val confirmPasswordVisible: Boolean = false,
)

sealed class ChangedPasswordEvents {
    data class OnPasswordChange(val input: String) : ChangedPasswordEvents()
    data class OnConfirmPasswordChange(val input: String) : ChangedPasswordEvents()
    object OnBackPressed : ChangedPasswordEvents()
    object OnPasswordEyeClicked : ChangedPasswordEvents()
    object OnConfirmPasswordEyeClicked : ChangedPasswordEvents()
    object OnSignUpClicked : ChangedPasswordEvents()
}
