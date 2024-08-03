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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.exam.sevenseven.R

lateinit var viewModel: WelcomeViewModel

@Composable
fun WelcomeScreen(
    onLogout: () -> Unit
) {
    viewModel = hiltViewModel()
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 200.dp, start = 40.dp, end = 40.dp)) {

            Text(
                modifier = Modifier.fillMaxWidth().padding(50.dp),
                text = stringResource(R.string.welcome),
                textAlign = TextAlign.Center,
                fontSize = 50.sp
            )

            Button(
                shape = RoundedCornerShape(40),
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp).height(50.dp),
                onClick = {
                    viewModel.logoutUser(onLogout)
                }
            ) {
                Text(text = stringResource(R.string.logout))
            }
        }
    }
}