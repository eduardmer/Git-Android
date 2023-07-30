package com.core_network.mapper

import com.core_model.Owner
import com.core_network.model.OwnerModel

fun OwnerModel.toDomainModel() = Owner(
    login.orEmpty(),
    id ?: -1,
    avatar_url.orEmpty()
)