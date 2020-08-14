package br.com.jera.moviejera.presentation.ui.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.jera.moviejera.domain.entities.User
import br.com.jera.moviejera.domain.usecases.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProfileViewModel @ViewModelInject constructor(
    private val userProfile: UserProfile
) : ViewModel() {

    private lateinit var _user: Flow<User>
    val user: Flow<User> get() = _user

    internal fun getUser(email: String?) {
        viewModelScope.launch {
            _user = userProfile.getUser(email)
        }
    }
}