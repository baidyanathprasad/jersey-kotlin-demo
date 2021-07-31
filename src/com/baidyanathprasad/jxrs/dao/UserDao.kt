package com.baidyanathprasad.jxrs.dao

import com.baidyanathprasad.jxrs.domain.UserInput.User
import jakarta.ws.rs.NotFoundException

object UserDao {
    private val users = mutableListOf<User>()

    fun add(user: User) {
        users.add(user)
    }

    fun get(): List<User> {
        return users
    }

    fun getById(id: Int): User {
        return users.firstOrNull { it.id == id }
            ?: throw NotFoundException("User not found in the system with given ID")
    }

    fun deleteById(id: Int): String {
        val user = users.firstOrNull { it.id == id }
            ?: throw NotFoundException("User not found in the system with given ID")

        users.remove(user)
        return user.name
    }
}
