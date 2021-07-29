package com.baidyanathprasad.jxrs.resources

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.xml.bind.annotation.XmlRootElement

@Path("/v3/users")
@Produces(MediaType.APPLICATION_JSON)
class UserResource {
    private val users = mutableListOf<User>()

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    fun createUser(user: User): Response {
        val existingUser = users.firstOrNull { it.id == user.id }
        if (existingUser != null) {
            return Response.ok("User with this ID is already available").build()
        }
        users.add(user)
        return Response.ok(user.asResponse()).build()
    }

    @GET
    fun getUsers(): Response {
        // Add a default users
        if (users.isEmpty()) {
            users.add(User(name = "Baidyanath", age = 27))
        }
        return users.map { it.asResponse() }.let { Response.ok(it).build() }
    }

    @XmlRootElement
    data class User(
        @JsonProperty("id")
        val id: String = UUID.randomUUID().toString(),

        @JsonProperty("name")
        val name: String,

        @JsonProperty("age")
        val age: Int
    ) {
        fun asResponse(): UserResponse {
            return UserResponse(
                id = id,
                name = name,
                age = age
            )
        }

        data class UserResponse(
            @JsonProperty("id")
            val id: String,

            @JsonProperty("name")
            val name: String,

            @JsonProperty("age")
            val age: Int,

            @JsonProperty("fetchedAt")
            val fetchedAt: Date = Date()
        )
    }
}
