package com.example.auth.data.api

import com.example.auth.data.model.ExchangeCodeRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @GET("1/auth/google/start")
    suspend fun getGoogleOAuthUri(): Response<Unit>

    @POST("1/auth/code/exchange")
    suspend fun exchangeCode(@Body request: ExchangeCodeRequest): Response<Unit>
}
