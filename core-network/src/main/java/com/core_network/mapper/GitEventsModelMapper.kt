package com.core_network.mapper

import com.core_model.GitEvents
import com.core_network.model.GitEventsModel
import com.core_network.model.toDomainModel

fun GitEventsModel.toDomainModel() = GitEvents(
    id.orEmpty(),
    type,
    actor.toDomainModel(),
    repo.toDomainModel(),
    payload.toDomainModel(),
    public ?: true,
    created_at
)