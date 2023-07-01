package com.core_domain.repository

import com.core_model.User
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUri: String
    ): Flow<String>

    fun login(user: User): Flow<User>

}