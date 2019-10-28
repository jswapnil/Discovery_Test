package com.assesment.discovery.model.network

import androidx.lifecycle.LiveData
import com.assesment.discovery.model.data.UserItem
import com.assesment.discovery.model.data.UserPost
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface UserService {

    @GET
    fun getUsers(@Url url: String): Observable<List<UserItem>>

    @GET
    fun getUserPosts(@Url url: String): Observable<List<UserPost>>
}