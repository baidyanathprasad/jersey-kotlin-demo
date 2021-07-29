package com.baidyanathprasad.jxrs.resources

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import java.util.*
import javax.xml.bind.annotation.XmlRootElement

@Path("/v3/users")
@Produces(MediaType.APPLICATION_JSON)
class UserResource {
    private val users = mutableListOf<User>()

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    fun createUser(request: User): Response {
        val existingUser = users.firstOrNull { it.name == request.name }
        if (existingUser != null) {
            return Response.ok("User with this ID is already available").build()
        }
        users.add(request)
        return Response.ok(request.asResponse()).build()
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
}
