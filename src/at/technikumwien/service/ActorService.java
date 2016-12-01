package at.technikumwien.service;

import at.technikumwien.entity.Actor;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    public List<Actor> getAllActors() {

        return em.createNamedQuery("Actor.selectAll", Actor.class).getResultList();

    }
}
