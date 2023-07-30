package com.core_persistance.mapper

import com.core_model.User
import com.core_persistance.UserPreferences

fun UserPreferences.toDomainModel() = User(
    login = login,
    id = id,
    avatar_url = avatarUrl,
    email = email,
    name = "",
    bio = bio,
    public_repos = publicRepos,
    public_gists = publicGists,
    followers = followers,
    following = following,
    created_at = createdAt,
    updated_at = updatedAt,
    private_gists = privateGists,
    total_private_repos = totalPrivateRepos,
    owned_private_repos = ownedPrivateRepos,
    collaborators = collaborators,
    token = token
)