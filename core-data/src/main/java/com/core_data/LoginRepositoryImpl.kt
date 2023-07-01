package com.core_data

import com.core_domain.repository.LoginRepository
import com.core_model.User
import com.core_network.service.LoginService
import com.core_persistance.PreferencesDataSource
import com.core_persistance.mapper.toDomainModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val service: LoginService,
    private val localDataSource: PreferencesDataSource
    ) : LoginRepository {

    override fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUri: String
    ) = flow {
        emit(service.getAccessToken(clientId, clientSecret, code, redirectUri).access_token)
    }

    override fun login(user: User) = flow {
        emit(localDataSource.login(user))
    }

}