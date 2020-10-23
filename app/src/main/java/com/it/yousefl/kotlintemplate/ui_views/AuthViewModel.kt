package com.it.yousefl.kotlintemplate.ui_views

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.it.yousefl.kotlintemplate.models.LoginModel
import com.it.yousefl.kotlintemplate.repository.AuthRepository
import com.it.yousefl.kotlintemplate.utils.AuthResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class AuthViewModel
@ViewModelInject
constructor(
    private val authRepository: AuthRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _authState: MutableLiveData<AuthResource<LoginModel>> = MutableLiveData()

    val authState: LiveData<AuthResource<LoginModel>> get() = _authState

    @ExperimentalStdlibApi
    fun setStateEvent(authStateevent: AuthStateevent, username: String, password: String) {
        viewModelScope.launch {
            when (authStateevent) {
                is AuthStateevent.GetBlogsEvent -> {
                    authRepository.login(username, password)
                        .onEach { dataState ->
                            _authState.value = dataState

                        }.launchIn(viewModelScope)
                }
                is AuthStateevent.None -> {
                    //who cares
                }
            }
        }
    }

}

sealed class AuthStateevent {

    object GetBlogsEvent : AuthStateevent()

    object None : AuthStateevent()
}