package com.core_model

import java.util.Date

data class GitEvents(
    val id: String,
    val type: EventType,
    val actor: Actor,
    val repo: Repo,
    val payload: Payload,
    val public: Boolean,
    val created_at: Date
)

data class Actor(
    val id: Int,
    val login: String,
    val avatar_url: String
)

data class Repo(
    val id: Int,
    val name: String,
    val url: String,
    var user_avatar_url: String = "",
    var description: String = "",
    var stargazers_count: Int = 0,
    var language: String = ""
)

data class Payload(
    val ref: String?,
    val ref_type: String,
    val master_branch: String,
    val description: String,
    val pusher_type: String,
    val forkee: Forkee
)

data class Forkee(
    val id: Long = -1,
    val node_id: String = "",
    val name: String = "",
    val full_name: String = "",
    val description: String = "",
    val stargazers_count: Int = 0,
    val watchers_count: Int = 0,
    val language: String = ""
)
