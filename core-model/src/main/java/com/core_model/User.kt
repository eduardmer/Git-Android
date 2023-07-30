package com.core_model

data class User(
    val login: String,
    val id: Long,
    val avatar_url: String,
    val email: String,
    val name: String,
    val bio: String,
    val public_repos: Int,
    val public_gists: Int,
    val followers: Int,
    val following: Int,
    val created_at: String,
    val updated_at: String,
    val private_gists: Int,
    val total_private_repos: Int,
    val owned_private_repos: Int,
    val collaborators: Int,
    val token: String,
    var starred: Int = 0,
    var orgs: Int = 0,
    var repos: List<Repository> = emptyList()
)