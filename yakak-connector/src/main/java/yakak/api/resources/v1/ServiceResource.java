package yakak.api.resources.v1;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/v1/service")
@Tag(name = "Service", description = "Operations related to the connector")
public class ServiceResource {
    
    @GET
    @Path("/status")
    public Response getStatus() {
        return Response.ok().build();
    }
}
