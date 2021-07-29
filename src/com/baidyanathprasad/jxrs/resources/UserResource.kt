package com.baidyanathprasad.jxrs.resources

import com.baidyanathprasad.jxrs.dao.UserDao
import com.baidyanathprasad.jxrs.domain.User
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/v3/users")
@Produces(MediaType.APPLICATION_JSON)
class UserResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    fun createUser(request: User): Response {
        val existingUser = UserDao.get().firstOrNull { it.name == request.name }
        if (existingUser != null) {
            return Response.ok("User with this ID is already available!").build()
        }
        UserDao.add(request)
        return Response.ok(request.asResponse()).build()
    }

    @GET
    fun getUsers(): Response {
        if (UserDao.get().isEmpty()) {
            return Response.ok("There is no users available in the system!").build()
        }
        return UserDao.get().map { it.asResponse() }.let { Response.ok(it).build() }
    }
}
