package com.core_domain.use_case

import com.core_domain.repository.LoginRepository
import com.core_domain.repository.UserRepository
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val userRepository: UserRepository) {

    operator fun invoke(clientId: String, clientSecret: String, code: String, redirectUri: String) =
        loginRepository.getAccessToken(clientId, clientSecret, code, redirectUri).flatMapLatest { token ->
            userRepository.getAuthenticatedUserData(token)
        }.flatMapLatest { user ->
            loginRepository.login(user)
        }

}