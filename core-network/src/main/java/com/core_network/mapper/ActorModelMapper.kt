package com.core_network.mapper

import com.core_model.Actor
import com.core_network.model.ActorModel

fun ActorModel.toDomainModel() = Actor(
    id ?: -1,
    login.orEmpty(),
    avatar_url.orEmpty()
)