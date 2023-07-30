package com.core_persistance

import androidx.datastore.core.DataStore
import com.core_model.User
import com.core_persistance.mapper.toDomainModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesDataSource @Inject constructor(private val dataStore: DataStore<UserPreferences>) {

    val user = dataStore.data.map {
        it.toDomainModel()
    }

    suspend fun login(user: User): User {
        return dataStore.updateData {
            it.toBuilder()
                .setLogin(user.login)
                .setId(user.id ?: 0)
                .setAvatarUrl(user.avatar_url)
                .setEmail(user.email ?: "")
                .setBio(user.bio ?: "")
                .setPublicRepos(user.public_repos)
                .setFollowers(user.followers ?: 0)
                .setFollowing(user.following ?: 0)
                .setTotalPrivateRepos(user.total_private_repos)
                .setToken(user.token ?: "")
                .build()
        }.toDomainModel()
    }

    suspend fun logout() {
        dataStore.updateData {
            it.toBuilder().clear().build()
        }
    }

}