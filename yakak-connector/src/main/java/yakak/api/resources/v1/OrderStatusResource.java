package yakak.api.resources.v1;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import yakak.api.models.entities.OrderStatus;
import yakak.api.services.OrderStatusService;

@Path("/order-status")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderStatusResource {
    @Inject
    OrderStatusService orderStatusService; 

    @GET
    @Path("/{id}")
    public Response getOrderStatusById(@PathParam("id") String id) {
        OrderStatus orderStatus = orderStatusService.findById(id);
        if (orderStatus != null) {
            return Response.ok(orderStatus).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response createOrderStatus(@Valid OrderStatus orderStatus) {
        OrderStatus createdOrderStatus = orderStatusService.createOrderStatus(orderStatus);
        return Response.status(Response.Status.CREATED).entity(createdOrderStatus).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateOrderStatus(@PathParam("id") String id, @Valid OrderStatus orderStatus) {
        OrderStatus updatedOrderStatus = orderStatusService.updateOrderStatus(id, orderStatus);
        if (updatedOrderStatus != null) {
            return Response.ok(updatedOrderStatus).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOrderStatus(@PathParam("id") String id) {
        boolean deleted = orderStatusService.deleteOrderStatus(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

