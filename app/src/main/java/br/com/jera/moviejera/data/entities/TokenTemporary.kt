package br.com.jera.moviejera.data.entities

import com.google.gson.annotations.SerializedName

data class TokenTemporary(
    @SerializedName("guest_session_id")
    val sessionId: String,
    val success: Boolean,
    @SerializedName("expires_at")
    val expiresAt: String
)