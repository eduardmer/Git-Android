package com.core_domain.use_case

import com.core_domain.repository.UserRepository
import com.core_model.Repository
import com.core_model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetReposUseCase @Inject constructor(private val userRepository: UserRepository) {

    operator fun invoke(user: User): Flow<List<Repository>> {
        return userRepository.getReposForUser(user)
    }

}