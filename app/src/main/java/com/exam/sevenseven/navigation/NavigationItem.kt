package com.exam.sevenseven.navigation

sealed class NavigationItem(val label: String, val screenRoute: String) {
    data object Login : NavigationItem("Login", "login_page")
    data object Welcome : NavigationItem("Welcome", "welcome_page")
}