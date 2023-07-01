package com.core_network.mapper

import com.core_model.Repository
import com.core_model.User
import com.core_network.model.RepositoryModel

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