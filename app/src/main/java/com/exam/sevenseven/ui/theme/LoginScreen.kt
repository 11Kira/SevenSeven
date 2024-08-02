package com.exam.sevenseven.ui.theme

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.exam.sevenseven.R

@Composable
fun LoginScreen() {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 200.dp, start = 40.dp, end = 40.dp)) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.account_login)
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                value = username.value,
                onValueChange = { username.value = it },
                leadingIcon = {
                    Icon(Icons.Default.AccountCircle, contentDescription = stringResource(R.string.username)) },
                label = {
                    Text(text = stringResource(R.string.username),)
                }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(top = 5.dp),
                value = password.value,
                onValueChange = { password.value = it },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = stringResource(R.string.password)) },
                visualTransformation = PasswordVisualTransformation(),
                label = {
                    Text(text = stringResource(R.string.password))
                }
            )
            
            Button(
                shape = RoundedCornerShape(40),
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp).height(50.dp),
                onClick = {
                    Log.e("test", username.value)
                }
            ) {
                Text(text = stringResource(R.string.login))
            }
        }
    }

}