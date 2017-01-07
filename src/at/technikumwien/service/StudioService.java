package at.technikumwien.service;

import at.technikumwien.entity.Movie;
import at.technikumwien.entity.Studio;
import at.technikumwien.resources.MoviesFilterInterceptor;
import at.technikumwien.resources.StudioFilterInterceptor;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by regula on 02.11.16.
 */
@Stateless
@SecurityDomain("MoviesSD")
public class StudioService {

	@Inject
	private MovieService ms;

	@PersistenceContext
	private EntityManager em;

	public StudioService() {
	}

	@RolesAllowed("BSRead")
	@Interceptors(StudioFilterInterceptor.class)
	public List<Studio> getAllStudios() {
		//LOGGER.info("getAllStudios() called");
		//LOGGER.debugf("> user=%s", ctx.getCallerPrincipal());
		//LOGGER.debugf("> inRole('MovieUser')=%b", ctx.isCallerInRole("MovieUser"));
		return em.createNamedQuery("Studio.selectAll", Studio.class).getResultList();
	}

	//CREATE
    @RolesAllowed("BSWrite")
    public void importStudio(Studio studio) {
		em.persist(studio);
	}

	//READ
    @RolesAllowed("BSRead")
	public Studio getStudio(Long studioId) {
		return em.find(Studio.class, studioId);
	}

	//UPDATE
	//so this is going to use the em.find method, which might have been a shorter way of checking the studio before :-)
    @RolesAllowed("BSWrite")
	public void updateStudio(Studio studio, Long studioId) {
		Studio studioOld = em.find(Studio.class, studioId);
		if(studioOld != null) {
			studioOld.setName(studio.getName());
			studioOld.setCountryCode(studio.getCountryCode());
			studioOld.setPostCode(studio.getPostCode());
			//anscheinen braucht man hier KEIN em.persist oder Ähnliches
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

	}

	//DELETE
    @RolesAllowed("BSWrite")
    public void deleteStudio(Long studioId) {
		Studio studioOld = em.find(Studio.class, studioId);
		if(studioOld != null) {
			Boolean deleteOK = true;
			List<Movie> movieList = ms.getAllMovies();
			for(Movie movie : movieList) {
				if (movie.getStudio().getStudioId() == studioId) {
					deleteOK = false;
					break;
				}
			}

			if(deleteOK) {
				em.remove(studioOld);
			} else {
				throw new EJBException("CANNOT DELETE STUDIO BECAUSE OF FOREIGN KEY CONSTRAINT");
			}
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}


	//HELPER METHODS FOR CREATING MOVIE
    @RolesAllowed("BSRead")
	public Studio getStudioFromDB (Studio studio) {
		Query query = em.createNamedQuery("Studio.select", Studio.class)
				.setParameter("name", studio.getName())
				.setParameter("countryCode", studio.getCountryCode())
				.setParameter("postCode", studio.getPostCode());

		Studio studioFromDB = (Studio) query.getSingleResult();

		return studioFromDB;
	}

    @RolesAllowed("BSRead")
	public Studio checkStudio(Studio studio) {
		if(!existsStudio(studio)) {
			throw new EJBException("can't find studio");
		} else {
			Studio managedStudio = getStudioFromDB(studio);
			return managedStudio;
		}
	}

    @RolesAllowed("BSRead")
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
