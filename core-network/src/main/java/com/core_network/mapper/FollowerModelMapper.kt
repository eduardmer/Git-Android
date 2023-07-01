package com.core_network.mapper

import com.core_model.Follower
import com.core_network.model.FollowerModel

fun FollowerModel.toDomainModel() = Follower(
    login = login.orEmpty(),
    id = id ?: -1,
    avatar_url = avatar_url.orEmpty()
)