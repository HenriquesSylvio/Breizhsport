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
import yakak.api.models.entities.Category;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource implements PanacheRepositoryBase<Category, UUID> {

    @GET
    public List<Category> getAllCategories() {
        return listAll(Sort.by("name"));
    }

    @GET
    @Path("/{id}")
    public Category getCategoryById(@PathParam("id") UUID id) {
        return findById(id);
    }

    @POST
    @Transactional
    public void createCategory(Category category) {
        persist(category);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void updateCategory(@PathParam("id") UUID id, Category updatedCategory) {
        Category currentCategory = findById(id);
        if (currentCategory != null) {
            currentCategory.setName(updatedCategory.getName());
            currentCategory.setDescription(updatedCategory.getDescription());
            currentCategory.setParentCategory(updatedCategory.getParentCategory());
            currentCategory.setChildren(updatedCategory.getChildren());
            persist(currentCategory);
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deleteCategory(@PathParam("id") UUID id) {
        deleteById(id);
    }
}
