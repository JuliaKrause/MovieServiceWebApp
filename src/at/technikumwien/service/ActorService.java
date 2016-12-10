package at.technikumwien.service;

import at.technikumwien.entity.Actor;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
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

    @PersistenceContext
    private EntityManager em;

    public ActorService() {
    }

    public List<Actor> getAllActors() {
        return em.createNamedQuery("Actor.selectAll", Actor.class).getResultList();

    }

    public void importActor(Actor actor) {
        System.out.println("Here we are");
        em.persist(actor);
    }

    //so this is going to use the em.find method, which might have been a shorter way of checking the studio before :-)
    public void updateActor(Actor actor, Long actorId) {
        Actor actorOld = em.find(Actor.class, actorId);
        if(actorOld != null) {
            actorOld.setFirstName(actor.getFirstName());
            actorOld.setLastName(actor.getLastName());
            actorOld.setSex(actor.getSex());
            actorOld.setBirthDate(actor.getBirthDate());
            //TODO: not sure if this needs this or an em.edit(studio) or if this line is not necessary at all:
            em.persist(actorOld);
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

    }

    //same here... :-)
    public void deleteActor(Long actorId) {
        Actor actorOld = em.find(Actor.class, actorId);
        if(actorOld != null) {
            //TODO: I'm sure I'm not allowed to delete an actor if its ID occurs in an entry in the movie_actor table
            // TODO: does the db take care of that or what?
            em.remove(actorOld);
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }



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
