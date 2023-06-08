package com.core_model

data class GitEvents(
    val id: String,
    val type: EventType,
    val actor: Actor,
    val repo: Repo,
    val payload: Payload,
    val public: Boolean,
    val created_at: String
)

data class Actor(
    val id: Int,
    val login: String,
    val avatar_url: String
)

data class Repo(
    val id: Int,
    val name: String,
    val url: String
)

data class Payload(
    val ref: String?,
    val ref_type: String,
    val master_branch: String,
    val description: String,
    val pusher_type: String
)
