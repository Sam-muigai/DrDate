package com.samkt.drdate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samkt.drdate.presentation.changePassword.ChangePasswordScreen
import com.samkt.drdate.presentation.forgotPassword.ForgotPasswordScreen
import com.samkt.drdate.presentation.loginScreen.LoginScreen
import com.samkt.drdate.presentation.otpScreen.OtpScreen
import com.samkt.drdate.presentation.signUpScreen.SignUpScreen
import com.samkt.drdate.ui.theme.DrDateTheme
import com.samkt.drdate.utils.Constants.ANIMATION_DURATION
import com.samkt.drdate.utils.Constants.CHANGE_PASSWORD_ROUTE
import com.samkt.drdate.utils.Constants.FORGOT_PASSWORD_ROUTE
import com.samkt.drdate.utils.Constants.LOGIN_ROUTE
import com.samkt.drdate.utils.Constants.OTP_ROUTE
import com.samkt.drdate.utils.Constants.SIGN_UP_ROUTE

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrDateTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = LOGIN_ROUTE,
        builder = {
            destination(LOGIN_ROUTE) {
                LoginScreen(navController)
            }
            destination(SIGN_UP_ROUTE) {
                SignUpScreen(navController = navController)
            }
            destination(FORGOT_PASSWORD_ROUTE) {
                ForgotPasswordScreen(navController = navController)
            }
            destination(OTP_ROUTE) {
                OtpScreen(navController = navController)
            }
            destination(CHANGE_PASSWORD_ROUTE) {
                ChangePasswordScreen(navController = navController)
            }
        },
    )
}

fun NavGraphBuilder.destination(
    route: String,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) {
    composable(
        route,
        content = content,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(ANIMATION_DURATION),
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(ANIMATION_DURATION),
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(ANIMATION_DURATION),
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(ANIMATION_DURATION),
            )
        },
    )
}
