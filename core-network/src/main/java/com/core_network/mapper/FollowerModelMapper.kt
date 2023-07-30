package com.core_network.mapper

import com.core_model.Follower
import com.core_network.model.FollowerModel

fun FollowerModel.toDomainModel() = Follower(
    id = id ?: -1,
    login = login.orEmpty(),
    avatar_url = avatar_url.orEmpty(),
    fullName = "",
    bio = ""
)