package com.exam.sevenseven

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.exam.sevenseven.ui.LoginScreen
import com.exam.sevenseven.ui.theme.SevenSevenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SevenSevenTheme {
                LoginScreen()
            }
        }
    }
}
