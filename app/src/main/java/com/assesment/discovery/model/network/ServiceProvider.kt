package com.assesment.discovery.model.network

object ServiceProvider {

    private var userService: UserService? = null

    fun getUserService(): UserService {
        if (userService == null) {
            userService = RetrofitFactory.createService(UserService::class.java)
        }
        return userService!!
    }
}