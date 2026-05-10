package com.example.auth.presentation.signin

sealed interface SignInIntent {

    data object ContinueWithGoogleClicked: SignInIntent
}

sealed interface SignInState {

    data object Idle: SignInState

    data object Loading: SignInState

    data object Success: SignInState

    data class Error(val message: String): SignInState
}
