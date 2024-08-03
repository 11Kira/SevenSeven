package com.exam.sevenseven

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.exam.sevenseven.navigation.NavigationItem
import com.exam.sevenseven.ui.login.LoginScreen
import com.exam.sevenseven.ui.theme.SevenSevenTheme
import com.exam.sevenseven.ui.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SevenSevenTheme {
                MainScreenView()
            }
        }
    }
}

@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    Scaffold { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            NavigationGraph(navController = navController)
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Login.screenRoute
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