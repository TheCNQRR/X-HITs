package com.example.auth.data.repository

import com.example.auth.data.api.AuthApi
import com.example.auth.data.model.ExchangeCodeRequest
import com.example.auth.domain.repository.AuthRepository
import javax.inject.Inject

@Suppress("TooGenericExceptionThrown")
class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun startGoogleAuth(): String {
        val response = api.getGoogleOAuthUri()

        val redirectUrl = response.headers()["Location"]

        if (!redirectUrl.isNullOrEmpty()) {
            return redirectUrl
        } else {
            throw Exception("No Location header found in response")
        }
    }

    override suspend fun exchangeCode(code: String): Boolean {
        val request = ExchangeCodeRequest(code)

        val response = api.exchangeCode(request)
        if (!response.isSuccessful) throw Exception("Exchange code failed")

        return true
    }
}
