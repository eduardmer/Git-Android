package com.core_network.model

import com.core_model.Payload

data class PayloadModel(
    val ref: String?,
    val ref_type: String?,
    val master_branch: String?,
    val description: String?,
    val pusher_type: String?
)

fun PayloadModel.toDomainModel() = Payload(
    ref.orEmpty(),
    ref_type.orEmpty(),
    master_branch.orEmpty(),
    description.orEmpty(),
    pusher_type.orEmpty()
)
