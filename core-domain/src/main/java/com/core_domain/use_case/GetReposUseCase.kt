package com.core_domain.use_case

import androidx.paging.PagingData
import com.core_domain.repository.UserRepository
import com.core_model.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class GetReposUseCase @Inject constructor(private val userRepository: UserRepository) {

    operator fun invoke(): Flow<PagingData<Repository>> =
        userRepository.user.flatMapLatest {
            userRepository.getReposForUser(it.token)
        }

}