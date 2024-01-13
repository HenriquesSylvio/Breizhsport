package yakak.api.resources.v1;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import yakak.api.models.entities.Orders;

@Path("/v1/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Orders", description = "Operations related to the customer Orders")
public class OrdersResource implements PanacheRepositoryBase<Orders, UUID> {

    @GET
    public List<Orders> getAllCarts() {
        return listAll(Sort.by("id"));
    }

    @GET
    @Path("/{id}")
    public Orders getCartById(@PathParam("id") UUID id) {
        return findById(id);
    }

    @POST
    @Transactional
    public void createCart(Orders cart) {
        persist(cart);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void updateCart(@PathParam("id") UUID id, Orders updatedCart) {
        Orders currentCart = findById(id);
        if (currentCart != null) {
            currentCart.setUser(updatedCart.getUser());
            currentCart.setProducts(updatedCart.getProducts());
            persist(currentCart);
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deleteCart(@PathParam("id") UUID id) {
        deleteById(id);
    }
}
