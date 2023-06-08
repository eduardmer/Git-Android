package com.core_network.service

import com.core_network.model.AccessTokenModel
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {

    @POST("login/oauth/access_token")
    @Headers("Accept: application/json")
    suspend fun getAccessToken(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("code") code: String,
        @Query("redirect_uri") redirectUri: String,
    ): AccessTokenModel

}