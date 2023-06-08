package com.core_domain.repository

import com.core_model.User

interface LoginRepository {

    suspend fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUri: String
    ): String

    suspend fun login(user: User)

}