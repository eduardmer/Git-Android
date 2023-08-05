package com.core_data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.core_domain.repository.UserRepository
import com.core_model.EventType
import com.core_model.Follower
import com.core_model.GitEvents
import com.core_model.Organization
import com.core_model.Repository
import com.core_model.User
import com.core_network.INITIAL_LOAD_SIZE
import com.core_network.LOAD_SIZE
import com.core_network.paging_source.RepositoryPagingSource
import com.core_network.paging_source.SearchPagingSource
import com.core_network.paging_source.StarredReposPagingSource
import com.core_network.mapper.toDomainModel
import com.core_network.model.RepositoryItem
import com.core_network.paging_source.OrganizationsPagingSource
import com.core_network.service.UserService
import com.core_persistance.PreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val service: UserService,
    private val searchPagingSource: SearchPagingSource,
    private val localDataSource: PreferencesDataSource
    ) : UserRepository {

    override val user = localDataSource.user

    override fun getAuthenticatedUserData(token: String) = flow {
        emit(service.getAuthenticatedUser("Bearer $token").toDomainModel(token))
    }

    override fun getUserData(token: String, username: String): Flow<User> = flow {
        emit(service.getUserData("Bearer $token", username).toDomainModel(token))
    }

    override fun getReposForAuthenticatedUser(token: String): Flow<List<Repository>> = flow {
        val repos = service.getReposForAuthenticatedUser("Bearer $token").map {
            it.toDomainModel()
        }
        emit(repos)
    }

    override fun getReposForUser(token: String): Flow<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(
                pageSize = LOAD_SIZE,
                prefetchDistance = LOAD_SIZE,
                enablePlaceholders = false,
                initialLoadSize = LOAD_SIZE
            )
        ) {
            RepositoryPagingSource(token, service)
        }.flow.map { pagingData ->
            pagingData.map {
                it.toDomainModel()
            }
        }
    }

    override fun getStarredReposByAuthUser(token: String): Flow<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(
                pageSize = LOAD_SIZE,
                prefetchDistance = LOAD_SIZE,
                enablePlaceholders = false,
                initialLoadSize = LOAD_SIZE
            )
        ) {
            StarredReposPagingSource(token, service)
        }.flow.map { pagingData ->
            pagingData.map {
                it.toDomainModel()
            }
        }
    }

    override fun getStarredReposByUser(token: String): Flow<List<Repository>> = flow {
        val repos = service.getStarredReposByUser("Bearer $token").map {
            it.toDomainModel()
        }
        emit(repos)
    }

    override fun getOrgsForAuthUser(token: String): Flow<PagingData<Organization>> {
        return Pager(
            config = PagingConfig(
                pageSize = LOAD_SIZE,
                prefetchDistance = LOAD_SIZE,
                enablePlaceholders = false,
                initialLoadSize = LOAD_SIZE
            )
        ) {
            OrganizationsPagingSource(token, service)
        }.flow.map { pagingData ->
            pagingData.map {
                it.toDomainModel()
            }
        }
    }

    override fun getOrganizations(token: String): Flow<List<Organization>> = flow {
        val orgs = service.getOrgs("Bearer $token").map {
            it.toDomainModel()
        }
        emit(orgs)
    }

    override fun getFollowers(token: String): Flow<List<Follower>> = flow {
        val followers = service.getFollowers("Bearer $token").map {
            it.toDomainModel()
        }
        followers.map {
            val userData = service.getUserData(token, it.login)
            it.fullName = userData.name ?: userData.login.orEmpty()
            it.bio = userData.bio.orEmpty()
        }
        emit(followers)
    }

    override fun getFollowing(token: String): Flow<List<Follower>> = flow {
        val following = service.getFollowing("Bearer $token").map {
            it.toDomainModel()
        }
        following.map {
            val userData = service.getUserData(token, it.login)
            it.fullName = userData.name ?: userData.login.orEmpty()
            it.bio = userData.bio.orEmpty()
        }
        emit(following)
    }

    override fun getEvents(user: User): Flow<List<GitEvents>> = flow {
        val events = service.getEvents(user.token, user.login).map {
            it.toDomainModel()
        }
        emit(events)
    }.flatMapConcat { events ->
        flow {
            events.map {
                if (it.type == EventType.WatchEvent) {
                    val repoData = service.getRepo(user.token, it.repo.name.split("/")[0], it.repo.name.split("/")[1])
                    it.repo.description = repoData.description.orEmpty()
                    it.repo.stargazers_count = repoData.stargazers_count ?: 0
                    it.repo.language = repoData.language.orEmpty()
                }
            }
            emit(events)
        }
    }

    override fun searchRepositories(): Flow<PagingData<RepositoryItem>> {
        return Pager(
            config = PagingConfig(LOAD_SIZE, LOAD_SIZE, false, INITIAL_LOAD_SIZE),
            pagingSourceFactory = { searchPagingSource }
        ).flow
    }

    override suspend fun logout() {
        localDataSource.logout()
    }

}