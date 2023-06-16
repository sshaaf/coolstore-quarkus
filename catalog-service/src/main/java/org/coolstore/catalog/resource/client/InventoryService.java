package org.coolstore.catalog.resource.client;

import org.coolstore.catalog.model.Inventory;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;



@RegisterRestClient
public interface InventoryService {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/inventory/{itemId}")
    Inventory getByItemId(@PathParam("itemId") String itemId);

}
