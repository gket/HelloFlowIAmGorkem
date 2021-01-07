package com.gk.helloflowiamgorkem.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UnsplashPhoto(
    @PrimaryKey
    val id: String,
    val created_at: String,
    val description: String,
    @Embedded
    val urls: PhotoUrl,
    @Embedded
    val user: User
)