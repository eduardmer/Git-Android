package com.core_domain.use_case

import com.core_domain.repository.UserRepository
import com.core_model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class GetAuthenticatedUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    operator fun invoke(): Flow<User> {
        return userRepository.user.flatMapLatest {
            combine(
                userRepository.getAuthenticatedUserData(it.token),
                userRepository.getReposForAuthenticatedUser(it.token),
                userRepository.getStarredReposByUser(it.token),
                userRepository.getOrganizations(it.token)
            ) { user, repos, starred, orgs ->
                user.repos = repos
                user.starred = starred.size
                user.orgs = orgs.size
                user
            }
        }
    }

}