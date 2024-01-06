package yakak.api.resources.v1;

import java.util.List;
import java.util.UUID;

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
import yakak.api.models.entities.CartItem;

@Path("/cart-items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartItemResource implements PanacheRepositoryBase<CartItem, UUID> {

    @GET
    public List<CartItem> getAllCartItems() {
        return listAll(Sort.by("id"));
    }

    @GET
    @Path("/{id}")
    public CartItem getCartItemById(@PathParam("id") UUID id) {
        return findById(id);
    }

    @POST
    @Transactional
    public void createCartItem(CartItem cartItem) {
        persist(cartItem);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void updateCartItem(@PathParam("id") UUID id, CartItem updatedCartItem) {
        CartItem currentCartItem = findById(id);
        if (currentCartItem != null) {
            currentCartItem.setCart(updatedCartItem.getCart());
            currentCartItem.setItem(updatedCartItem.getItem());
            currentCartItem.setQuantity(updatedCartItem.getQuantity());
            // currentCartItem.setUnitPrice(updatedCartItem.getUnitPrice());
            // currentCartItem.setTotalPrice(updatedCartItem.getTotalPrice());
            persist(currentCartItem);
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deleteCartItem(@PathParam("id") UUID id) {
        deleteById(id);
    }
}
