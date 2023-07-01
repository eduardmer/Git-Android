package com.core_network.model

data class UserModel(
    val login: String?,
    val id: Long?,
    val avatar_url: String?,
    val url: String?,
    val type: String?,
    val email: String?,
    val bio: String?,
    val public_repos: Int?,
    val public_gists: Int?,
    val followers: Int?,
    val following: Int?,
    val created_at: String?,
    val updated_at: String?,
    val private_gists: Int?,
    val total_private_repos: Int?,
    val owned_private_repos: Int?,
    val disk_usage: Int?,
    val collaborators: Int?,
    val two_factor_authentication: Boolean
)
