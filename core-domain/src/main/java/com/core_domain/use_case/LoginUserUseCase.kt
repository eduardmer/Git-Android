package com.core_domain.use_case

import com.core_domain.repository.LoginRepository
import com.core_domain.repository.UserRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val userRepository: UserRepository) {

    suspend operator fun invoke(clientId: String, clientSecret: String, code: String, redirectUri: String) {
        val token = loginRepository.getAccessToken(clientId, clientSecret, code, redirectUri)
        val user = userRepository.getUserData(token)
        loginRepository.login(user)
    }

}