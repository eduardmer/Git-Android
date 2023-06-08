package com.core_network.model

import com.core_model.Repo

data class RepoModel(
    val id: Int?,
    val name: String?,
    val url: String?
)

fun RepoModel.toDomainModel() = Repo(
    id ?: -1,
    name.orEmpty(),
    url.orEmpty()
)
