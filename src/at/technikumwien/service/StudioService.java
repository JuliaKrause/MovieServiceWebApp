package at.technikumwien.service;

import at.technikumwien.entity.Actor;
import at.technikumwien.entity.Studio;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

	public long getStudioIdFromDB (Studio studio) {
		Studio studioFromDB = (Studio) em.createQuery("SELECT n FROM Studio n WHERE " +
				"n.name ='" + studio.getName() + "' and " +
				"n.countryCode ='" + studio.getCountryCode() + "' and " +
				"n.postCode ='" + studio.getPostCode() + "'")
				.getSingleResult();
		long studioIdFromDB = studioFromDB.getStudioId();
		return studioIdFromDB;
	}

	public void checkStudio(Studio studio) {
		if(!existsStudio(studio)) {
			throw new EJBException("can't find studio");
		} else {
			long studioId = getStudioIdFromDB(studio);
			System.out.println("Studio ID IS: ");
			System.out.println(studioId);
			studio.setStudioId(studioId);
		}
	}


	public Boolean existsStudio(Studio studio) {
		// todo: geht das nicht irgendwie mit named Queries?
		List<Object> rl = em.createQuery("SELECT n FROM Studio n WHERE " +
				"n.name ='" + studio.getName() + "' and " +
				"n.countryCode ='" + studio.getCountryCode() + "' and " +
				"n.postCode ='" + studio.getPostCode() + "'")
				.getResultList();
		if (rl.size() == 1) {
			return true;
		} else {
			return false;
		}
	}
}
