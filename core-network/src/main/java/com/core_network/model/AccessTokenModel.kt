package com.core_network.model

data class AccessTokenModel(
    val access_token: String,
    val expires_in: Int?,
    val refresh_token: String?,
    val refresh_token_expires_in: Int?,
    val scope: String,
    val token_type: String
)
