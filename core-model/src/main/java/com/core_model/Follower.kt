package com.core_model

data class Follower(
    val id: Long,
    val login: String,
    val avatar_url: String,
    var fullName: String,
    var bio: String
)
