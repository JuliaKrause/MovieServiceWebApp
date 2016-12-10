package at.technikumwien.resources;

import at.technikumwien.entity.Actor;
import at.technikumwien.service.ActorService;

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

@Path("/actor")
@Transactional
public class ActorResource {
    @Inject
    private ActorService actorService;
    @PersistenceContext
    private EntityManager em;
    @Context
    private UriInfo ui;


    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createActor(Actor actor) {
        actorService.importActor(actor);
        URI studioURI = ui.getAbsolutePathBuilder().path(actor.getActorId().toString()).build();
        return Response.created(studioURI).build();
    }

    @PUT
    @Path("/{actorId}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void updateActor(@PathParam("actorId") Long actorId, Actor actor) {
        actorService.updateActor(actor, actorId);
    }

    /*@GET
    @Path("/{studioId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getStudioAsString(@PathParam("studioId") Long studioId) {
        Studio studio = em.find(Studio.class, studioId);
        return (studio != null ? studio.toString() : null);
    }*/

    /*@GET
    @Path("/{studioId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Studio getStudioAsJSON(@PathParam("studioId") Long studioId) {
        return em.find(Studio.class, studioId);
    }*/

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Actor> getAllActors() {
        return actorService.getAllActors();
    }

    @DELETE
    @Path("/{actorId}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void deleteActor(@PathParam("actorId") Long actorId, Actor actor) {
        //not sure what I would need parameter actor for
        actorService.deleteActor(actorId);
    }


}
