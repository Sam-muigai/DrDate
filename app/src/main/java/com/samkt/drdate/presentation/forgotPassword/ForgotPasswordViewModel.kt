package com.samkt.drdate.presentation.forgotPassword

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ForgotPasswordViewModel : ViewModel() {
    private val _forgotPasswordState = MutableStateFlow(ForgotPasswordState())
    val forgotPasswordState = _forgotPasswordState.asStateFlow()

    fun onEmailChange(input: String) {
        _forgotPasswordState.update {
            it.copy(
                email = input,
            )
        }
    }
}

data class ForgotPasswordState(
    val email: String = "",
)

sealed class ForgotPasswordEvents {
    data class OnEmailChange(val input: String) : ForgotPasswordEvents()
    object OnSendCode : ForgotPasswordEvents()
    object OnBackPressed : ForgotPasswordEvents()
}
