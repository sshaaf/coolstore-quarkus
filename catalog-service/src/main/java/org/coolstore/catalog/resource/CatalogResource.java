package org.coolstore.catalog.resource;


import org.coolstore.catalog.model.Product;
import org.coolstore.catalog.service.CatalogService;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.openapi.annotations.Operation;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/products")
@Tag(name = "products")
@Produces(APPLICATION_JSON)
public class CatalogResource {

    @Inject
    CatalogService service;

    private static final Logger logger = Logger.getLogger(CatalogResource.class);
    @GET
    public Response getAll() {
        return Response.ok(service.getAllProductsWithQuantity()).build();

    }

    @GET
    @Path("/{itemId}")
    public Response getOne(@PathParam("itemId") String itemId) {
        Product entity = service.findById(itemId);
        if (entity == null) {
            logger.debug("product with id of " + itemId + " does not exist.");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(entity).build();
    }

    @POST
    @Operation(summary = "Create a new product")
    @Transactional
    @Consumes(APPLICATION_JSON)
    public Response create(@Valid Product product) {
        try {
            service.create(product);
            return Response.status(Response.Status.CREATED).entity(product).build();
        }
        catch (PersistenceException pe){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/{itemId}")
    @Transactional
    public Response update(@Valid Product product, @PathParam("itemId") String itemId) {
        Product entity = service.update(itemId);
        if (entity == null) {
            logger.debug("product with id of " + itemId + " does not exist.");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        else
            return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{itemId}")
    public Response deleteOne(@PathParam("itemId") String itemId) {
        Product entity = service.delete(itemId);
        if (entity == null) {
            throw new WebApplicationException("product with itemId of " + itemId + " does not exist.", Response.Status.NOT_FOUND);
        }
        return Response.noContent().build();
    }
}