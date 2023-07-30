package com.core_network.mapper

import com.core_model.Forkee
import com.core_model.Payload
import com.core_network.model.PayloadModel

fun PayloadModel.toDomainModel() = Payload(
    ref.orEmpty(),
    ref_type.orEmpty(),
    master_branch.orEmpty(),
    description.orEmpty(),
    pusher_type.orEmpty(),
    forkee?.toDomainModel() ?: Forkee()
)