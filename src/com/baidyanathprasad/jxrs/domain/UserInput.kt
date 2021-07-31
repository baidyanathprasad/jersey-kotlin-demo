package com.baidyanathprasad.jxrs.domain

import com.baidyanathprasad.jxrs.common.convertDateToLocalDate
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
data class UserInput(
    @JsonProperty("name")
    val name: String,

    @JsonProperty("age")
    val age: Int
) {
    fun asModel(): User {
        return User(
            id = 0,
            name = name,
            age = age,
            createdAt = Date()
        )
    }

    data class User(
        val id: Int,
        val name: String,
        val age: Int,
        val createdAt: Date
    ) {
        fun asResponse(): UserResponse {
            return UserResponse(
                id = id,
                name = name,
                age = age,
                createdAt = convertDateToLocalDate(createdAt).toString()
            )
        }
    }

    data class UserResponse(
        @JsonProperty("id")
        val id: Int,

        @JsonProperty("name")
        val name: String,

        @JsonProperty("age")
        val age: Int,

        @JsonProperty("createdAt")
        val createdAt: String
    )
}
