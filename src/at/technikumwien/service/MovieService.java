package at.technikumwien.service;

import at.technikumwien.MovieList;
import at.technikumwien.entity.*;
import at.technikumwien.resources.MoviesFilterInterceptor;
import org.jboss.ejb3.annotation.SecurityDomain;


import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 * Created by Julia on 30.10.2016.
 */
//transaktionen etc kommt hierher. also alles, was mit der db direkt zu tun hat.
@Stateless
@SecurityDomain("MoviesSD")
public class MovieService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private ActorService as;

    @Inject
    private StudioService ss;

    //@Resource
    //private SessionContext ctx;


    @RolesAllowed("BSRead")
    @Interceptors(MoviesFilterInterceptor.class)
    public List<Movie> getAllMovies() {
        /*System.out.println("**********************************************************");
        System.out.println(em.toString());
        System.out.println(em.getEntityManagerFactory());
        System.out.println("**********************************************************");*/
        //System.out.println("THIS IS THE GETALLMOVIES-METHOD IN MOVIESERVICE!!!");
        return em.createNamedQuery("Movie.selectAll", Movie.class).getResultList();

    }

    @RolesAllowed("BSRead")
    public List<Movie> searchMoviesByTitle(String searchString) {
        System.out.println("THIS IS THE SEARCHMOVIESBYTITLE-METHOD IN MOVIESERVICE!!!");
        return em.createQuery("SELECT n FROM Movie n WHERE n.title LIKE '%" + searchString + "%'").getResultList();
    }

    @RolesAllowed("BSWrite")
    public void importMovies(MovieList movieList) {
    	List<Movie> myMovieList = movieList.getMovieList();
        for(Movie movie : myMovieList) {
            System.out.println("MOVIE IN IMPORT LIST " + movie.toString());

            as.checkActors(movie.getActorList());
			movie.setStudio(ss.checkStudio(movie.getStudio()));

			System.out.println("importing movie " + movie.getTitle() + " in MovieService");
			em.persist(movie);

		}
    }

}