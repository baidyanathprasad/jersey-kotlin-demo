package com.baidyanathprasad.jxrs.domain

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
data class User(
    @JsonProperty("name")
    val name: String,

    @JsonProperty("age")
    val age: Int
) {
    fun asResponse(): UserResponse {
        return UserResponse(
            name = name,
            age = age
        )
    }

    data class UserResponse(
        @JsonProperty("name")
        val name: String,

        @JsonProperty("age")
        val age: Int,

        @JsonProperty("fetchedAt")
        val fetchedAt: Date = Date()
    )
}