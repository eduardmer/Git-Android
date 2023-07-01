package com.core_domain.repository

import androidx.paging.PagingData
import com.core_model.Follower
import com.core_model.GitEvents
import com.core_model.Repository
import com.core_model.User
import com.core_network.model.RepositoryItem
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    val user: Flow<User>

    fun getAuthenticatedUserData(token: String): Flow<User>

    fun getReposForUser(user: User): Flow<List<Repository>>

    fun getFollowers(token: String): Flow<List<Follower>>

    fun getFollowing(token: String): Flow<List<Follower>>

    fun getEvents(username: String): Flow<List<GitEvents>>

    fun searchRepositories(): Flow<PagingData<RepositoryItem>>

    suspend fun logout()

}