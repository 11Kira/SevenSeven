package com.exam.sevenseven.ui.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.exam.sevenseven.R
import kotlinx.coroutines.flow.SharedFlow

lateinit var viewModel: WelcomeViewModel

@Composable
fun WelcomeScreen(
    onLogout: () -> Unit
) {
    viewModel = hiltViewModel()
    MainScreen(viewModel.welcomeState, onLogout)
}

@Composable
fun MainScreen(sharedFlow: SharedFlow<WelcomeState>, onLogout: () -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var username by remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            sharedFlow.collect { state ->
                when (state) {
                    is WelcomeState.SetUsername -> {
                        username = state.username
                    }
                }
            }
        }
    }
    SetupWelcomeScreen(username, onLogout)
}
@Composable
fun SetupWelcomeScreen(username: String, onLogout: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 200.dp, start = 40.dp, end = 40.dp)) {

            Text(
                modifier = Modifier.fillMaxWidth().padding(start = 50.dp, end = 50.dp),
                text = stringResource(R.string.welcome),
                textAlign = TextAlign.Center,
                fontSize = 50.sp
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = username,
                textAlign = TextAlign.Center,
                fontSize = 25.sp
            )
            Button(
                shape = RoundedCornerShape(40),
                modifier = Modifier.fillMaxWidth().padding(top = 40.dp).height(50.dp),
                onClick = {
                    viewModel.logoutUser(onLogout)
                }
            ) {
                Text(text = stringResource(R.string.logout))
            }
        }
    }
}