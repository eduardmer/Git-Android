package com.core_network.model

data class PayloadModel(
    val ref: String?,
    val ref_type: String?,
    val master_branch: String?,
    val description: String?,
    val pusher_type: String?
)
