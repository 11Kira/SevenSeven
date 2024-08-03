package com.exam.sevenseven.ui.welcome

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exam.sevenseven.user.UserPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    @ApplicationContext val context: Context
): ViewModel() {

    private val _mutableWelcomeState: MutableSharedFlow<WelcomeState> = MutableSharedFlow()
    val welcomeState
        get() = _mutableWelcomeState.asSharedFlow()

    fun logoutUser(onLogout: () -> Unit) {
        viewModelScope.launch {
            UserPrefs(context).apply {
                removeUserLogin()
                removeUsername()
            }
            withContext(Dispatchers.Main) {
                onLogout.invoke()
            }
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val username = UserPrefs(context).getUsername.first().toString()
            withContext(Dispatchers.Main) {
                _mutableWelcomeState.emit(WelcomeState.SetUsername(username))
            }
        }
    }
}