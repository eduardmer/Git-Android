package com.core_network.mapper

import com.core_model.Organization
import com.core_network.model.OrganizationModel

fun OrganizationModel.toDomainModel() = Organization(
    login.orEmpty(),
    id ?: -1,
    node_id.orEmpty(),
    description.orEmpty()
)