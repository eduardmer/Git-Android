package com.core_data

import com.core_domain.repository.LoginRepository
import com.core_model.User
import com.core_network.service.LoginService
import com.core_persistance.PreferencesDataSource
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val service: LoginService,
    private val localDataSource: PreferencesDataSource
    ) : LoginRepository {

    override suspend fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUri: String
    ): String {
        return service.getAccessToken(clientId, clientSecret, code, redirectUri).access_token
    }

    override suspend fun login(user: User) {
        localDataSource.login(user)
    }

}