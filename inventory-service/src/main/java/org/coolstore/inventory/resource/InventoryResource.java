package org.coolstore.inventory.resource;

import org.coolstore.inventory.entity.Inventory;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.stream.Collectors;

@Path("/api/inventory")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InventoryResource {

    @GET
    @Path("/{itemId}")
    public Inventory getAvailability(@PathParam("itemId") String itemId) {

        // TODO , make it collective instead. inventory could have more then 1 entry
        return Inventory.<Inventory>streamAll()
                .filter(p -> p.itemId.equals(itemId))
                .collect(Collectors.toList()).get(0);

    }

    @GET
    public Response getAll() {
        return Response.ok(Inventory.listAll()).build();
    }

}
