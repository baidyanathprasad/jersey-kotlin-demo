package com.baidyanathprasad.jxrs.resources

import com.baidyanathprasad.jxrs.dao.UserDao
import com.baidyanathprasad.jxrs.domain.UserInput
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/api/v3/users")
@Produces(MediaType.APPLICATION_JSON)
class UserResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    fun createUser(request: UserInput): Response {
        val existingUser = UserDao.get().firstOrNull { it.name == request.name }
        if (existingUser != null) {
            return Response.ok("User with this ID is already available!").build()
        }

        val userId = if (UserDao.get().isEmpty()) 1 else UserDao.get().size + 1
        UserDao.add(request.asModel().copy(id = userId))

        val user = UserDao.getById(userId)
        return Response.ok(user.asResponse()).build()
    }

    @GET
    fun getUsers(): Response {
        if (UserDao.get().isEmpty()) {
            return Response.ok("There is no users available in the system!").build()
        }
        return UserDao.get().map { it.asResponse() }.let { Response.ok(it).build() }
    }

    @GET
    @Path("/{userId}")
    fun getUserById(@PathParam("userId") userId: Int): Response {
        val user = UserDao.getById(userId)

        return Response.ok(user.asResponse()).build()
    }

    @DELETE
    @Path("/{userId}")
    fun deleteUserById(@PathParam("userId") userId: Int): Response {
        val user = UserDao.deleteById(userId)

        return Response.ok("$user remove from system \uD83D\uDE14").build()
    }
}
