package at.technikumwien.service;

import at.technikumwien.entity.Actor;
import at.technikumwien.entity.Studio;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
