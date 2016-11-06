package at.technikumwien.service;

import at.technikumwien.entity.*;



import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 * Created by Julia on 30.10.2016.
 */
//transaktionen etc kommt hierher. also alles, was mit der db direkt zu tun hat.
@Stateless
public class MovieService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private ActorService as;

    @Inject
    private StudioService ss;

    public List<Movie> getAllMovies() {
        /*System.out.println("**********************************************************");
        System.out.println(em.toString());
        System.out.println(em.getEntityManagerFactory());
        System.out.println("**********************************************************");*/
        System.out.println("THIS IS THE GETALLMOVIES-METHOD IN MOVIESERVICE!!!");
        return em.createNamedQuery("Movie.selectAll", Movie.class).getResultList();

    }

    public List<Movie> searchMoviesByTitle(String searchString) {
        System.out.println("THIS IS THE SEARCHMOVIESBYTITLE-METHOD IN MOVIESERVICE!!!");
        return em.createQuery("SELECT n FROM Movie n WHERE n.title LIKE '%" + searchString + "%'").getResultList();
    }

    //Ein Film darf nur dann importiert werden, wenn alle zugehörigen Schauspieler
    //und das zugehörige Studio bereits in der Datenbank vorhanden sind
    //für jeden tatsächlich vorhandenen Schauspieler hole ich mir aus der Datenbank die Id
    //und setze sie dann für diesen Schauspieler in der Schauspieler-Liste des Films
    //genauso für Studio
    //das scheint zu reichen, damit der entity manager das persist machen kann
    //die studio id erscheint als Fremdschluessel in der Movie Tabelle
    //die entsprechende Zeile wird in der movie_actor Tabelle hinzugefügt
    //wenn mehrere Filme hinzugefuegt werden sollen und einer nicht geht, dann wird aber anscheinend denen,
    //die gehen wuerden, eine Id gegeben, denn beim naechsten erfolgreichen Hinzufuegen von Filmen
    //sieht man in der db, dass Ids uebersprungen wurden

    public void importMovies(List<Movie> movieList) {
    	for(Movie movie : movieList) {
            System.out.println("MOVIE IN IMPORT LIST " + movie.toString());

            as.checkActors(movie.getActorList());
			ss.checkStudio(movie.getStudio());

			System.out.println("importing movie " + movie.getTitle() + " in MovieService");
			em.persist(movie);

		}
    }

}