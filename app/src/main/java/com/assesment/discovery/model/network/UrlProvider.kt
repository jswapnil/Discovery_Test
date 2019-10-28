package com.assesment.discovery.model.network

import android.net.Uri

const val BASE_URL = "https://jsonplaceholder.typicode.com"

fun getUsersUrl(): String {
    val uri = Uri.parse(BASE_URL)
        .buildUpon()
        .appendPath("users")
        .build()
    return uri.toString()
}

fun getUserPosts(userId: Int): String {
    val uri = Uri.parse(BASE_URL)
        .buildUpon()
        .appendPath("posts")
        .appendQueryParameter("userId", userId.toString())
        .build()
    return uri.toString()
}