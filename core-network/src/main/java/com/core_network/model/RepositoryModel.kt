package com.core_network.model

data class RepositoryModel(
    val id: Long?,
    val node_id: String?,
    val name: String?,
    val full_name: String?,
    val description: String?,
    val fork: Boolean?,
    val homepage: String?,
    val size: Int?,
    val stargazers_count: Int?,
    val watchers_count: Int?,
    val language: String?,
    val forks_count: Int?,
    val visibility: String?,
    val watchers: Int?,
    val default_branch: String?,
    val owner: OwnerModel?
)
