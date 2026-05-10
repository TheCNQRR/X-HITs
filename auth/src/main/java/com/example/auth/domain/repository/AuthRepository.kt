package com.example.auth.domain.repository

interface AuthRepository {

    suspend fun startGoogleAuth(): String

    suspend fun exchangeCode(code: String): Boolean
}
