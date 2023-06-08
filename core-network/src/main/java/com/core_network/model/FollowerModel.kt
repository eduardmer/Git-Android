package com.core_network.model

import com.core_model.Follower

data class FollowerModel(
    val login: String?,
    val id: Long?,
    val avatar_url: String?
)

fun FollowerModel.toDomainModel() = Follower(
    login = login.orEmpty(),
    id = id ?: -1,
    avatar_url = avatar_url.orEmpty()
)