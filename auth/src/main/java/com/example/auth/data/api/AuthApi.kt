package com.example.auth.data.api

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

data class GoogleAuthUrlResponse(val url: String)

interface AuthApi {

    @GET("oauth/google/url")
    suspend fun getGoogleOAuthUri(): GoogleAuthUrlResponse

    @POST("oauth/google/exchange")
    @FormUrlEncoded
    suspend fun exchangeCode(@Field("code") code: String): Response<Unit>
}
