package at.technikumwien.resources;

import at.technikumwien.entity.Studio;
import at.technikumwien.service.StudioService;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;


/**
 * Created by Julia on 08.12.2016.
 */


//die anderen getter eventuell wieder einkommentieren

@Path("/studio")
@Transactional
public class StudioResource {
    @Inject
    private StudioService studioService;

    @Context
    private UriInfo ui;


    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createStudio(Studio studio) {
        studioService.importStudio(studio);
        URI studioURI = ui.getAbsolutePathBuilder().path(studio.getStudioId().toString()).build();
        return Response.created(studioURI).build();
    }

    /*@GET
    @Path("/{studioId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getStudioAsString(@PathParam("studioId") Long studioId) {
        Studio studio = em.find(Studio.class, studioId);
        return (studio != null ? studio.toString() : null);
    }*/

    @GET
    @Path("/{studioId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Studio getStudioAsJSON(@PathParam("studioId") Long studioId) {
        return studioService.getStudio(studioId);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Studio> getAllStudios() {

        return studioService.getAllStudios();
    }

    @PUT
    @Path("/{studioId}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void updateStudio(@PathParam("studioId") Long studioId, Studio studio) {
        studioService.updateStudio(studio, studioId);
    }

    @DELETE
    @Path("/{studioId}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void deleteStudio(@PathParam("studioId") Long studioId) {
        studioService.deleteStudio(studioId);
    }


}


