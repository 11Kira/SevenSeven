package com.exam.sevenseven.ui.welcome

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exam.sevenseven.user.UserPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    @ApplicationContext val context: Context
): ViewModel() {

    private lateinit var username: String

    init {
        viewModelScope.launch(Dispatchers.IO) {
            username =  async { UserPrefs(context).getUsername.first().toString() }.await()
        }
    }

    fun logoutUser(onLogout: () -> Unit) {
        viewModelScope.launch {
            UserPrefs(context).removeUserLogin()
            withContext(Dispatchers.Main) {
                onLogout.invoke()
            }
        }
    }

    fun getUsername(): String {
        return username
    }
}