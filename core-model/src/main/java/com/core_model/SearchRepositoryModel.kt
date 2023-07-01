package com.core_network.model

import com.core_model.OwnerModel

data class SearchRepositoryModel(
    val total_count: Long,
    val incomplete_results: Boolean?,
    val items: List<RepositoryItem>
)

data class RepositoryItem(
    val id: Long?,
    val node_id: String?,
    val name: String?,
    val full_name: String?,
    val description: String?,
    val stargazers_count: Int?,
    val watchers_count: Int?,
    val language: String?,
    val forks_count: Int?,
    val owner: OwnerModel
)
