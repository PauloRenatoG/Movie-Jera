package br.com.jera.moviejera.presentation.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.jera.moviejera.domain.entities.User
import br.com.jera.moviejera.domain.usecases.UserProfile
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(
    private val userProfile: UserProfile
) : ViewModel() {

    internal fun saveUser(currentUser: FirebaseUser) {
        viewModelScope.launch {
            val user = User(
                name = currentUser.displayName,
                email = currentUser.email,
                photoUrl = currentUser.photoUrl.toString()
            )
            userProfile.execute(user)
        }
    }
}