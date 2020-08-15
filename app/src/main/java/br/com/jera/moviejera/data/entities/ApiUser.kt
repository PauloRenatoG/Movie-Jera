package br.com.jera.moviejera.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class ApiUser(
    @PrimaryKey
    val id: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val dateOfBirth: String? = null,
    val photoUrl: String? = null
)