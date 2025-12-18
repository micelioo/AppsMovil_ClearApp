package com.example.et_clearapp.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Usuario(
    @SerializedName(value = "id")
    val id: Long? = null,

    @SerializedName(value = "email")
    val email: String,

    @SerializedName(value = "passwordHash", alternate = ["password_hash"])
    val passwordHash: String? = null,

    @SerializedName(value = "creadoEn", alternate = ["creado_en"])
    val creadoEn: LocalDateTime? = null
)
