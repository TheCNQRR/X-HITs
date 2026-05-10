package com.example.auth.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _state = MutableStateFlow<SignInState>(SignInState.Idle)

    val state: StateFlow<SignInState> = _state.asStateFlow()

    fun dispatchIntent(intent: SignInIntent) {
        when (intent) {
            SignInIntent.ContinueWithGoogleClicked -> {
                continueWithGoogle()
            }
        }
    }

    private fun continueWithGoogle() {
        viewModelScope.launch {
            _state.value = SignInState.Loading

            runCatching {
                authRepository.startGoogleAuth()
            }.onSuccess {
                _state.value = SignInState.Success
            }.onFailure {
                _state.value = SignInState.Error(message = "Failed to open Google auth")
            }
        }
    }
}
