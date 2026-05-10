package com.example.auth.presentation.signin

sealed interface SignInIntent {

    data object ContinueWithGoogleClicked: SignInIntent
}

sealed interface SignInState {

    data object Idle: SignInIntent

    data object Loading: SignInIntent

    data object Success: SignInIntent

    data class Error(val message: String): SignInIntent
}
