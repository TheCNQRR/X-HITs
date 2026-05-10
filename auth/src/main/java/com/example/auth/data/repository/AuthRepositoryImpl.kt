package com.example.auth.data.repository

import com.example.auth.data.api.AuthApi
import com.example.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
): AuthRepository {

    override suspend fun startGoogleAuth(): String = api.getGoogleOAuthUri().url

    override suspend fun exchangeCode(code: String): Boolean {
        val response = api.exchangeCode(code)
        if (!response.isSuccessful) throw Exception("Exchange code failed")

        return true
    }
}
