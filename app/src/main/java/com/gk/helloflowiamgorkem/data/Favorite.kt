package com.gk.helloflowiamgorkem.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey
    val id: String,
    val url: String?,
    val user: String?
)