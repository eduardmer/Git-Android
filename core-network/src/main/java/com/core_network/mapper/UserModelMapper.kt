package com.core_network.mapper

import com.core_model.User
import com.core_network.model.UserModel

fun UserModel.toDomainModel(token: String): User = User(
    login = login.orEmpty(),
    id = id ?: -1,
    avatar_url = avatar_url.orEmpty(),
    url = url.orEmpty(),
    type = type.orEmpty(),
    email = email.orEmpty(),
    bio = bio.orEmpty(),
    public_repos = public_repos ?: 0,
    public_gists = public_gists ?: 0,
    followers = followers ?: 0,
    following = following ?: 0,
    created_at = created_at.orEmpty(),
    updated_at = updated_at.orEmpty(),
    private_gists = private_gists ?: 0,
    total_private_repos = total_private_repos ?: 0,
    owned_private_repos = owned_private_repos ?: 0,
    disk_usage = disk_usage ?: 0,
    collaborators = collaborators ?: 0,
    two_factor_authentication = two_factor_authentication,
    token = token,
    repos = emptyList()
)