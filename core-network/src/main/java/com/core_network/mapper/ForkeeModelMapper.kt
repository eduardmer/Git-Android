package com.core_network.mapper

import com.core_model.Forkee
import com.core_model.Payload
import com.core_network.model.ForkeeModel
import com.core_network.model.PayloadModel

fun ForkeeModel.toDomainModel() = Forkee(
    id ?: -1,
    node_id.orEmpty(),
    name.orEmpty(),
    full_name.orEmpty(),
    description.orEmpty(),
    stargazers_count ?: 0,
    watchers_count ?: 0,
    language.orEmpty()
)