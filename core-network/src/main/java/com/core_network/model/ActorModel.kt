package com.core_network.model

import com.core_model.Actor

data class ActorModel(
    val id: Int?,
    val login: String?,
    val avatar_url: String?
)

fun ActorModel.toDomainModel() = Actor(
    id ?: -1,
    login.orEmpty(),
    avatar_url.orEmpty()
)
