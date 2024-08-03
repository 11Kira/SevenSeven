package com.exam.sevenseven.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exam.sevenseven.R
import com.exam.sevenseven.user.UserCredential
import com.exam.sevenseven.user.UserPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext val context: Context
): ViewModel() {

    private val _mutableUsernameError = MutableStateFlow("")
    val usernameError
        get() = _mutableUsernameError.asStateFlow()

    private fun setUsernameError(usernameError: String) {
        _mutableUsernameError.value = usernameError
    }

    private val _mutablePasswordError = MutableStateFlow("")
    val passwordError
        get() = _mutablePasswordError.asStateFlow()

    private fun setPasswordError(passwordError: String) {
        _mutablePasswordError.value = passwordError
    }

    private fun validateUsername(username :String): Boolean {
        var isValid = true
        var errorMessage = ""
        if (username.isBlank() || username.isEmpty()) {
            errorMessage = context.getString(R.string.username_should_not_be_empty)
            isValid = false
        } else if (username.length < 5) {
            errorMessage = context.getString(R.string.username_length_error)
            isValid = false
        }
        setUsernameError(errorMessage)
        return isValid
    }

    private fun validatePassword(password: String): Boolean {
        var isValid = true
        var errorMessage = ""
        if (password.isBlank() || password.isEmpty()) {
            errorMessage = context.getString(R.string.enter_password)
            isValid = false
        } else if (password.length < 5) {
            errorMessage = context.getString(R.string.password_length_error)
            isValid = false
        }
        setPasswordError(errorMessage)
        return isValid
    }

    fun validateFields(userCredential: UserCredential, onLogin: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO ) {
            if (validateUsername(userCredential.username) && validatePassword(userCredential.password)) {
                UserPrefs(context).apply {
                    setUserLogin(true)
                    setUsername(userCredential.username)
                }
                withContext(Dispatchers.Main) {
                    onLogin.invoke()
                }
            }
        }
    }
}