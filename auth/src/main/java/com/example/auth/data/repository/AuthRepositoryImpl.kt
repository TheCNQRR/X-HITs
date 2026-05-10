package com.example.auth.data.repository

import com.example.auth.data.remote.oauth.GoogleOAuthLauncher
import com.example.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val googleOAuthLauncher: GoogleOAuthLauncher
): AuthRepository {

    override suspend fun startGoogleAuth() {
        googleOAuthLauncher.launch()
    }

    override suspend fun exchangeCode(code: String?): Result<Unit> {
        return Result.success(Unit)
    }
}
