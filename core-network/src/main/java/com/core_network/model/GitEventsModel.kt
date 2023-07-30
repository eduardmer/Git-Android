package com.core_network.model

import com.core_model.EventType
import java.util.Date

data class GitEventsModel(
    val id: String?,
    val type: EventType,
    val actor: ActorModel,
    val repo: RepoModel,
    val payload: PayloadModel,
    val public: Boolean?,
    val created_at: Date
)
