package com.core_domain.use_case

import com.core_domain.repository.UserRepository
import com.core_model.Follower
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class GetFollowersUseCase @Inject constructor(private val userRepository: UserRepository) {

    operator fun invoke(): Flow<List<Follower>> =
        userRepository.user.flatMapLatest {
            userRepository.getFollowers(it.token)
        }

}