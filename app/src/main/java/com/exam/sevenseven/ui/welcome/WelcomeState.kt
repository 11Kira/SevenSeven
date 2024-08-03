package com.exam.sevenseven.ui.welcome

sealed class WelcomeState {
    data class SetUsername(val username: String): WelcomeState()
}