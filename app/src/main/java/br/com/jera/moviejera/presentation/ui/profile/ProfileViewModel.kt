package br.com.jera.moviejera.presentation.ui.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.jera.moviejera.domain.entities.User
import br.com.jera.moviejera.domain.usecases.UserProfile
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileViewModel @ViewModelInject constructor(
    private val userProfile: UserProfile
) : ViewModel() {

    private lateinit var _user: Flow<User>
    val user: Flow<User> get() = _user

    private val _complete: MutableLiveData<Boolean> = MutableLiveData()
    val complete: LiveData<Boolean> get() = _complete

    internal fun getUser(email: String?) {
        viewModelScope.launch {
            _user = userProfile.getUser(email)
        }
    }

    @InternalCoroutinesApi
    internal fun updateUser(name: String, email: String, dateOfBirth: String) {
        viewModelScope.launch {
            user.collect {
                val userCopy = User(
                    id = it.id,
                    name = name,
                    email = email,
                    dateOfBirth = dateOfBirth,
                    photoUrl = it.photoUrl
                )
                runCatching {
                    userProfile.update(userCopy)
                }.onSuccess {
                    _complete.value = true
                }
            }
        }
    }
}