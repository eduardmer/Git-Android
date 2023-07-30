package com.core_network.model

data class ForkeeModel(
    val id: Long?,
    val node_id: String?,
    val name: String?,
    val full_name: String?,
    val description: String?,
    val stargazers_count: Int?,
    val watchers_count: Int?,
    val language: String?
)
