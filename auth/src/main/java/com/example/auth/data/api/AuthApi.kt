package com.example.auth.data.api

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @GET("1/auth/google/start")
    suspend fun getGoogleOAuthUri(): Response<Unit>

    @POST("1/auth/code/exchange")
    @FormUrlEncoded
    suspend fun exchangeCode(@Field("code") code: String): Response<Unit>
}
