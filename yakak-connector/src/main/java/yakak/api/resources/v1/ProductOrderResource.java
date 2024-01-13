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
import yakak.api.models.entities.ProductOrder;

@Path("/v1/product-order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "product-order", description = "Operations related to managing products from order")
public class ProductOrderResource implements PanacheRepositoryBase<ProductOrder, UUID> {

    @GET
    public List<ProductOrder> getAllCartItems() {
        return listAll(Sort.by("id"));
    }

    @GET
    @Path("/{id}")
    public ProductOrder getCartItemById(@PathParam("id") UUID id) {
        return findById(id);
    }

    @POST
    @Transactional
    public void createCartItem(ProductOrder cartItem) {
        persist(cartItem);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void updateCartItem(@PathParam("id") UUID id, ProductOrder updatedProductOrder) {
        ProductOrder currentProductOrder = findById(id);
        if (currentProductOrder != null) {
            currentProductOrder.setOrder(updatedProductOrder.getOrder());
            currentProductOrder.setProduct(updatedProductOrder.getProduct());
            currentProductOrder.setQuantity(updatedProductOrder.getQuantity());
            persist(currentProductOrder);
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deleteCartItem(@PathParam("id") UUID id) {
        deleteById(id);
    }
}
