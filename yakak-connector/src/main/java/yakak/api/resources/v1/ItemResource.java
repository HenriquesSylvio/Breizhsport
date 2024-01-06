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
import yakak.api.models.entities.Item;

@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemResource implements PanacheRepositoryBase<Item, UUID> {

    @GET
    public List<Item> getAllItems() {
        return listAll(Sort.by("name"));
    }

    @GET
    @Path("/{id}")
    public Item getItemById(@PathParam("id") UUID id) {
        return findById(id);
    }

    @POST
    @Transactional
    public void createItem(Item item) {
        persist(item);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void updateItem(@PathParam("id") UUID id, Item updatedItem) {
        Item currentItem = findById(id);
        if (currentItem != null) {
            currentItem.setName(updatedItem.getName());
            currentItem.setDescription(updatedItem.getDescription());
            currentItem.setPrice(updatedItem.getPrice());
            currentItem.setStockQuantity(updatedItem.getStockQuantity());
            currentItem.setCategory(updatedItem.getCategory());
            persist(currentItem);
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deleteItem(@PathParam("id") UUID id) {
        deleteById(id);
    }
}
