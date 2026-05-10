package com.example.auth.domain.repository

interface AuthRepository {
    suspend fun startGoogleAuth()

    suspend fun exchangeCode(code: String?): Result<Unit>
}
