package at.technikumwien.service;

import at.technikumwien.entity.Studio;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by regula on 02.11.16.
 */
@Stateless
public class StudioService {

	@PersistenceContext
	private EntityManager em;

	public StudioService() {
	}

	public List<Studio> getAllStudios() {
		return em.createNamedQuery("Studio.selectAll", Studio.class).getResultList();

	}

	public void importStudio(Studio studio) {
		em.persist(studio);
	}

	//so this is going to use the em.find method, which might have been a shorter way of checking the studio before :-)
	public void updateStudio(Studio studio, Long studioId) {
		Studio studioOld = em.find(Studio.class, studioId);
		if(studioOld != null) {
			studioOld.setName(studio.getName());
			studioOld.setCountryCode(studio.getCountryCode());
			studioOld.setPostCode(studio.getPostCode());
			//TODO: not sure if this needs this or an em.edit(studio) or if this line is not necessary at all:
			em.persist(studioOld);
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

	}

	//same here... :-)
	public void deleteStudio(Long studioId) {
		Studio studioOld = em.find(Studio.class, studioId);
		if(studioOld != null) {
			//TODO: I'm sure I'm not allowed to delete a studio if there is a movie that has its ID as a foreign key
			//TODO: does the db take care of that or what?
			em.remove(studioOld);
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}


	public Studio getStudioFromDB (Studio studio) {
		Query query = em.createNamedQuery("Studio.select", Studio.class)
				.setParameter("name", studio.getName())
				.setParameter("countryCode", studio.getCountryCode())
				.setParameter("postCode", studio.getPostCode());

		Studio studioFromDB = (Studio) query.getSingleResult();

		return studioFromDB;
	}

	public Studio checkStudio(Studio studio) {
		if(!existsStudio(studio)) {
			throw new EJBException("can't find studio");
		} else {
			Studio managedStudio = getStudioFromDB(studio);
			return managedStudio;
		}
	}


	public Boolean existsStudio(Studio studio) {
		Query query = em.createNamedQuery("Studio.select", Studio.class)
				.setParameter("name", studio.getName())
				.setParameter("countryCode", studio.getCountryCode())
				.setParameter("postCode", studio.getPostCode());

		List<Object> rl = query.getResultList();

		if (rl.size() == 1) {
			return true;
		} else {
			return false;
		}

	}
}
