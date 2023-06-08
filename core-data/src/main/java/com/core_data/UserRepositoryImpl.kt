package com.core_data

import com.core_domain.repository.UserRepository
import com.core_model.Follower
import com.core_model.GitEvents
import com.core_model.Repository
import com.core_model.User
import com.core_network.model.toDomainModel
import com.core_network.service.UserService
import com.core_persistance.PreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val service: UserService,
    private val localDataSource: PreferencesDataSource
    ) : UserRepository {

    override val user = localDataSource.user

    override suspend fun getUserData(token: String): User {
        return service.getUser("Bearer $token").toDomainModel(token)
    }

    override fun getReposForUser(user: User): Flow<List<Repository>> = flow {
        val repos = service.getReposForUser("Bearer ${user.token}").map {
            it.toDomainModel(user)
        }
        emit(repos)
    }

    override fun getFollowers(token: String): Flow<List<Follower>> = flow {
        val followers = service.getFollowers("Bearer $token").map {
            it.toDomainModel()
        }
        emit(followers)
    }

    override fun getFollowing(token: String): Flow<List<Follower>> = flow {
        val following = service.getFollowing("Bearer $token").map {
            it.toDomainModel()
        }
        emit(following)
    }

    override fun getEvents(username: String): Flow<List<GitEvents>> = flow {
        val events = service.getEvents(username).map {
            it.toDomainModel()
        }
        emit(events)
    }

    override suspend fun logout() {
        localDataSource.logout()
    }

}