package at.technikumwien.service;

import at.technikumwien.entity.Actor;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

	public long getActorIdFromDB (Actor actor) {
		Actor actorFromDB = (Actor) em.createQuery("SELECT n FROM Actor n WHERE " +
				"n.firstName ='" + actor.getFirstName() + "' and " +
				"n.lastName ='" + actor.getLastName() + "' and " +
				"n.sex ='" + actor.getSex() + "' and " +
				"n.birthDate = '" + new java.sql.Date(actor.getBirthDate().getTime()) + "'")
				.getSingleResult();
		long actorIdFromDB = actorFromDB.getActorId();
		return actorIdFromDB;
	}


	public void checkActors(List<Actor> actorList) {
		for (Actor actor : actorList) {
			if(!existsActor(actor)){
				throw new EJBException("can't find actor");
			} else {
				long actorId = getActorIdFromDB(actor);
				System.out.println("ACTOR ID IS: ");
				System.out.println(actorId);
				actor.setActorId(actorId);
			}
		}
	}

	public Boolean existsActor(Actor actor) {
		// todo: geht das nicht irgendwie mit named Queries?
		/*System.out.println("**********************************************************");
		System.out.println(em.toString());
		System.out.println(em.getEntityManagerFactory());
		System.out.println("**********************************************************");*/
		List<Object> rl = em.createQuery("SELECT n FROM Actor n WHERE " +
				"n.firstName ='" + actor.getFirstName() + "' and " +
				"n.lastName ='" + actor.getLastName() + "' and " +
				"n.sex ='" + actor.getSex() + "' and " +
				"n.birthDate = '" + new java.sql.Date(actor.getBirthDate().getTime()) + "'")
				.getResultList();
		if (rl.size() == 1) {
			return true;
		} else {
			return false;
		}
	}
}
