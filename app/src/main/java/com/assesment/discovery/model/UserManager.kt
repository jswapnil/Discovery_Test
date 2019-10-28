package com.assesment.discovery.model

import com.assesment.discovery.model.data.UserPost
import com.assesment.discovery.model.network.ServiceProvider
import com.assesment.discovery.model.network.getUserPosts
import com.assesment.discovery.model.network.getUsersUrl
import io.reactivex.Observable

object UserManager {


    fun getUsers(): Observable<List<UserPost>> {
        val userResponseObservable = ServiceProvider.getUserService().getUsers(getUsersUrl())

        return userResponseObservable
            .switchMap {
                ServiceProvider.getUserService().getUserPosts(getUserPosts(it.first().id))
            }
    }

}