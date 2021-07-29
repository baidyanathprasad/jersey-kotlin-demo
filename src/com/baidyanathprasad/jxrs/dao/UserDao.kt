package com.baidyanathprasad.jxrs.dao

import com.baidyanathprasad.jxrs.domain.User

object UserDao {
    private val users = mutableListOf<User>()

    fun add(user: User) {
       users.add(user)
    }

    fun get(): List<User> {
        return users
    }
}