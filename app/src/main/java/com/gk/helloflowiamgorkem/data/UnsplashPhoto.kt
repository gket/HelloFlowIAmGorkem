package com.gk.helloflowiamgorkem.data

data class UnsplashPhoto(
    val id: String,
    val created_at: String,
    val description: String,
    val urls: PhotoUrl,
    val user: User
)