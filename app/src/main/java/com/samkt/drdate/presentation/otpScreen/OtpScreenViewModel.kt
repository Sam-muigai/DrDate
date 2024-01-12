package com.samkt.drdate.presentation.otpScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OtpScreenViewModel : ViewModel() {
    private val _otpScreenState = MutableStateFlow(OtpScreenState())
    val otpScreenState = _otpScreenState.asStateFlow()

    fun onCodeChange(input: String) {
        _otpScreenState.update {
            it.copy(
                code = input,
            )
        }
    }
}

data class OtpScreenState(
    val code: String = "",
)

sealed class OtpScreenEvent {
    data class OnCodeChange(val input: String) : OtpScreenEvent()
    object OnBackPressed : OtpScreenEvent()
    object OnVerifyClicked : OtpScreenEvent()
}
