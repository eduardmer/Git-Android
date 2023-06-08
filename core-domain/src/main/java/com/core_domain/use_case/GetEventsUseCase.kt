package com.core_domain.use_case

import com.core_domain.repository.UserRepository
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(private val userRepository: UserRepository) {

    operator fun invoke() = userRepository.user.flatMapLatest {
        userRepository.getEvents(it.login)
    }

}