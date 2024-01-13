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
import yakak.api.models.entities.Product;

@Path("/v1/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Product", description = "Operations related to the Yakak products")
public class ProductResource implements PanacheRepositoryBase<Product, UUID> {

    @GET
    public List<Product> getAllProducts() {
        return listAll(Sort.by("name"));
    }

    @GET
    @Path("/{id}")
    public Product getProductById(@PathParam("id") UUID id) {
        return findById(id);
    }

    @POST
    @Transactional
    public void createProduct(Product product) {
        persist(product);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void updateProduct(@PathParam("id") UUID id, Product updatedProduct) {
        Product currentProduct = findById(id);
        if (currentProduct != null) {
            currentProduct.setName(updatedProduct.getName());
            currentProduct.setDescription(updatedProduct.getDescription());
            currentProduct.setPrice(updatedProduct.getPrice());
            currentProduct.setStockQuantity(updatedProduct.getStockQuantity());
            currentProduct.setCategory(updatedProduct.getCategory());
            persist(currentProduct);
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deleteProduct(@PathParam("id") UUID id) {
        deleteById(id);
    }
}
