package com.exam.sevenseven

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.exam.sevenseven.navigation.NavigationItem
import com.exam.sevenseven.ui.login.LoginScreen
import com.exam.sevenseven.ui.theme.SevenSevenTheme
import com.exam.sevenseven.ui.welcome.WelcomeScreen
import com.exam.sevenseven.user.UserPrefs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    var isUserLoggedIn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            isUserLoggedIn = UserPrefs(this@MainActivity).isLogin.first()
            setContent {
                SevenSevenTheme {
                    MainScreenView(isUserLoggedIn)
                }
            }
        }
    }
}

@Composable
fun MainScreenView(isUserLoggedIn: Boolean) {
    val navController = rememberNavController()
    Scaffold { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            NavigationGraph(navController = navController, isUserLoggedIn)
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, isUserLoggedIn: Boolean) {
    Log.e("testLogin", isUserLoggedIn.toString())
    NavHost(
        navController = navController,
        startDestination = if (isUserLoggedIn) {
            NavigationItem.Welcome.screenRoute
        } else {
            NavigationItem.Login.screenRoute
        }
    ) {
        composable(NavigationItem.Login.screenRoute) {
            LoginScreen(
                onLogin = {
                    navController.navigate(NavigationItem.Welcome.screenRoute)
                }
            )
        }
        composable(NavigationItem.Welcome.screenRoute) {
            WelcomeScreen(
                onLogout = {
                    navController.navigate(NavigationItem.Login.screenRoute)
                }
            )
        }
    }
}