package com.exam.sevenseven.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.exam.sevenseven.R

lateinit var viewModel: LoginViewModel

@Composable
fun LoginScreen(
) {
    viewModel = hiltViewModel()

    val usernameInput = remember { mutableStateOf("") }
    val passwordInput = remember { mutableStateOf("") }
    var isPasswordHidden by remember { mutableStateOf(true) }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 200.dp, start = 40.dp, end = 40.dp)) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.account_login),
                textAlign = TextAlign.Center,
                fontSize = 30.sp
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                value = usernameInput.value,
                onValueChange = { usernameInput.value = it },
                leadingIcon = {
                    Icon(
                        Icons.Default.AccountCircle,
                        contentDescription = stringResource(R.string.username),
                        modifier = Modifier
                            .height(20.dp)
                            .width(20.dp)
                    ) },
                label = {
                    Text(text = stringResource(R.string.username))
                },
                isError = viewModel.usernameError.collectAsState().value.isNotEmpty()
            )

            if (viewModel.usernameError.collectAsState().value.isNotEmpty()) {
                Text(
                    text = viewModel.usernameError.collectAsState().value,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Red,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 2.dp),
                    textAlign = TextAlign.Start
                )
            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                value = passwordInput.value,
                onValueChange = { passwordInput.value = it },
                leadingIcon = {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = stringResource(R.string.password),
                        modifier = Modifier
                            .height(20.dp)
                            .width(20.dp)
                    ) },
                visualTransformation = if (isPasswordHidden) {
                    PasswordVisualTransformation()
                } else VisualTransformation.None,
                label = {
                    Text(text = stringResource(R.string.password))
                },
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .height(20.dp)
                            .clickable(true) {
                                isPasswordHidden = !isPasswordHidden
                            },
                        painter = if (isPasswordHidden) {
                            painterResource(id = R.drawable.ic_visible)
                        } else painterResource(id = R.drawable.ic_invisible),
                        contentDescription = null
                    )
                },
                isError = viewModel.passwordError.collectAsState().value.isNotEmpty()
            )
            if (viewModel.passwordError.collectAsState().value.isNotEmpty()) {
                Text(
                    text = viewModel.passwordError.collectAsState().value,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Red,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 2.dp),
                    textAlign = TextAlign.Start
                )
            }
            
            Button(
                shape = RoundedCornerShape(40),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .height(50.dp),
                onClick = {
                    viewModel.validateFields(usernameInput.value, passwordInput.value)
                }
            ) {
                Text(text = stringResource(R.string.login))
            }
        }
    }

}