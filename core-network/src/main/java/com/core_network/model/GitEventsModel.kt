package com.core_network.model

import com.core_model.EventType
import com.core_model.GitEvents

data class GitEventsModel(
    val id: String?,
    val type: EventType,
    val actor: ActorModel,
    val repo: RepoModel,
    val payload: PayloadModel,
    val public: Boolean?,
    val created_at: String?
)

fun GitEventsModel.toDomainModel() = GitEvents(
    id.orEmpty(),
    type,
    actor.toDomainModel(),
    repo.toDomainModel(),
    payload.toDomainModel(),
    public ?: true,
    created_at.orEmpty()
)
