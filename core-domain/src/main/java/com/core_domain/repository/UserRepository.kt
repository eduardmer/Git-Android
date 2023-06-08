package com.core_domain.repository

import com.core_model.Follower
import com.core_model.GitEvents
import com.core_model.Repository
import com.core_model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    val user: Flow<User>

    suspend fun getUserData(token: String): User

    fun getReposForUser(user: User): Flow<List<Repository>>

    fun getFollowers(token: String): Flow<List<Follower>>

    fun getFollowing(token: String): Flow<List<Follower>>

    fun getEvents(username: String): Flow<List<GitEvents>>

    suspend fun logout()

}