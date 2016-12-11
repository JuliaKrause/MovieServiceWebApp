package at.technikumwien.service;

import at.technikumwien.entity.Actor;
import at.technikumwien.entity.Movie;
import at.technikumwien.resources.ActorResource;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * Created by regula on 02.11.16.
 */
@Stateless
public class ActorService {

    @Inject
    private MovieService ms;

    @PersistenceContext
    private EntityManager em;

    public ActorService() {
    }

    public List<Actor> getAllActors() {
        return em.createNamedQuery("Actor.selectAll", Actor.class).getResultList();

    }

    //CREATE
    public void importActor(Actor actor) {
        em.persist(actor);
    }

    //READ
    public Actor getActor(Long actorId) {
        return em.find(Actor.class, actorId);
    }

    //UPDATE
    //so this is going to use the em.find method, which might have been a shorter way of checking the studio before :-)
    public void updateActor(Actor actor, Long actorId) {
        Actor actorOld = em.find(Actor.class, actorId);
        if(actorOld != null) {
            actorOld.setFirstName(actor.getFirstName());
            actorOld.setLastName(actor.getLastName());
            actorOld.setSex(actor.getSex());
            actorOld.setBirthDate(actor.getBirthDate());
            //anscheinen braucht man hier KEIN em.persist oder Ähnliches
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    //DELETE
    public void deleteActor(Long actorId) {
        Actor actorOld = em.find(Actor.class, actorId);
        if(actorOld != null) {
            Boolean deleteOK = true;
            List<Movie> movieList = ms.getAllMovies();

            for(Movie movie : movieList) {
                List<Actor> actorList = movie.getActorList();
                for (Actor actor : actorList) {
                    if (actor.getActorId() == actorId) {
                        deleteOK = false;
                        break;
                    }
                }
            }

            if(deleteOK) {
                em.remove(actorOld);
            } else {
                throw new EJBException("CANNOT DELETE STUDIO BECAUSE OF FOREIGN KEY CONSTRAINT");
            }
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }





    //HELPER METHODS FOR CREATING MOVIE
    //TODO: INSTEAD OF THIS, USE EM.FIND HERE AS WELL
    public Actor getActorFromDB (Actor actor) {
        Query query = em.createNamedQuery("Actor.select", Actor.class)
                .setParameter("firstName", actor.getFirstName())
                .setParameter("lastName", actor.getLastName())
                .setParameter("sex", actor.getSex())
                .setParameter("birthDate", new java.sql.Date(actor.getBirthDate().getTime()));

        Actor actorFromDB = (Actor) query.getSingleResult();

        return actorFromDB;
    }

    public void checkActors(List<Actor> actorList) {
        for (Actor actor : actorList) {
            if(!existsActor(actor)){
                throw new EJBException("can't find actor");
            } else {
                Actor managedActor = getActorFromDB(actor);
                actorList.set(actorList.indexOf(actor), managedActor);
            }
        }
    }

    public Boolean existsActor(Actor actor) {
        Query query = em.createNamedQuery("Actor.select", Actor.class)
                .setParameter("firstName", actor.getFirstName())
                .setParameter("lastName", actor.getLastName())
                .setParameter("sex", actor.getSex())
                .setParameter("birthDate", new java.sql.Date(actor.getBirthDate().getTime()));

        List<Object> rl = query.getResultList();

        if (rl.size() == 1) {
            return true;
        } else {
            return false;
        }
    }

}
