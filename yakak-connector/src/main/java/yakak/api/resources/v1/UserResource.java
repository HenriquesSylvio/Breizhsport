package yakak.api.resources.v1;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import yakak.api.models.entities.User;
import yakak.api.services.UserService;

@Path("/v1/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @POST
    public User createUser(User user) {
        return userService.createUser(user.getFirstName(), user.getLastName(), user.getPassword(), user.getEmail(), user.getRole());
    }

    @GET
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GET
    @Path("/{id}")
    public User getUserById(@PathParam("id") Long id) {
        return userService.getUserById(id);
    }

    @DELETE
    @Path("/{id}")
    public boolean deleteUser(@PathParam("id") Long id) {
        return userService.deleteUser(id);
    }

    @PUT
    @Path("/{id}")
    public User updateUser(@PathParam("id") Long id, User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }
}
