package com.gk.helloflowiamgorkem.data

import com.google.gson.annotations.SerializedName

data class UnsplashPhoto(
    @SerializedName("id")
    val id: String,
    @SerializedName("created_at")
    val createdAt: String,
    val description: String,
    @SerializedName("urls")
    val url: PhotoUrl
)