package com.core_domain.use_case

import com.core_domain.repository.UserRepository
import com.core_model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class GetAuthenticatedUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    operator fun invoke(): Flow<User> {
        return userRepository.user.flatMapLatest {
            userRepository.getAuthenticatedUserData(it.token).zip(userRepository.getReposForUser(it)) { user, repos ->
                user.repos = repos
                user
            }
        }
    }

}