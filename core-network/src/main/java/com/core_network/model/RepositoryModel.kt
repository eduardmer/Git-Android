package com.core_network.model

import com.core_model.Repository
import com.core_model.User

data class RepositoryModel(
    val id: Long?,
    val node_id: String?,
    val name: String?,
    val full_name: String?,
    val description: String?,
    val fork: Boolean?,
    val homepage: String?,
    val size: Int?,
    val stargazers_count: Int?,
    val watchers_count: Int?,
    val language: String?,
    val forks_count: Int?,
    val visibility: String?,
    val watchers: Int?,
    val default_branch: String?
)

fun RepositoryModel.toDomainModel(user: User) = Repository(
    id = id ?: -1,
    node_id = node_id.orEmpty(),
    name = name.orEmpty(),
    full_name = full_name.orEmpty(),
    description = description.orEmpty(),
    fork = fork ?: false,
    homepage = homepage.orEmpty(),
    size = size ?: 0,
    stargazers_count = stargazers_count ?: 0,
    watchers_count = watchers_count ?: 0,
    language = language.orEmpty(),
    forks_count = forks_count ?: 0,
    visibility = visibility.orEmpty(),
    watchers = watchers ?: 0,
    default_branch = default_branch.orEmpty(),
    user = user
)
