package at.technikumwien;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Julia on 30.10.2016.
 */

@Stateless
public class MovieService {

    @PersistenceContext
    private EntityManager em;

    public List<Movie> getAllMovies() {
        System.out.println("**********************************************************");
        System.out.println(em.toString());
        System.out.println(em.getEntityManagerFactory());
        System.out.println("**********************************************************");
        return em.createNamedQuery("Movie.selectAll", Movie.class).getResultList();

    }

    public List<Movie> searchMoviesByTitle(String searchString) {
        System.out.println("**********************************************************");
        System.out.println(em.toString());
        System.out.println(em.getEntityManagerFactory());
        System.out.println("**********************************************************");
        return em.createQuery("SELECT n FROM Movie n WHERE n.title LIKE '%" + searchString + "%'").getResultList();

    }

    public Boolean existsActor(Actor actor) {
        String firstName = actor.getFirstName();
        String lastName = actor.getLastName();
        String sex = actor.getSex();
        Date birthDate = actor.getBirthDate();
        //String bd = birthDate.toString();
        //String bd = "2016-09-09";
        //String bd = null;
        int count = em.createQuery("SELECT n FROM Actor n WHERE n.firstName ='" + firstName
        + "' and n.lastName ='" + lastName + "' and n.sex ='" + sex + "'").getResultList().size();
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void addMovie(Movie movie) {
        System.out.println("**********************************************************");
        System.out.println(em.toString());
        System.out.println(em.getEntityManagerFactory());
        System.out.println("**********************************************************");
        for(Actor actor : movie.getActorList()) {
            if(!existsActor(actor)){
                throw new EJBException("can't find actor");
            }
        }
        em.persist(movie);

    }

    public void addMovies(List<Movie> movieList) {
        System.out.println("**********************************************************");
        System.out.println(em.toString());
        System.out.println(em.getEntityManagerFactory());
        System.out.println("**********************************************************");
        for(Movie movie : movieList) {
            em.persist(movie);
        }

    }


    //aber von wo aus wird diese Methode aufgerufen?
    public void importMovies(List<Movie> movieList) {
        for (Movie movie : movieList) {
            //Ein Film darf nur dann importiert werden, wenn alle zugehörigen Schauspieler
            //und das zugehörige Studio bereits in der Datenbank vorhanden sind
            //überprüfe schauspieler durch query und ersetze
            //schauspieler-objekt durch geladenen (MANAGED!)
            //schauspieler - leider weiss ich nicht, was das heisst

            //throw new EJBException("can't find actor");//this means that NO movie will be persisted if one does not work
            //gleiches für studio
            em.persist(movie);//andere for-Schleife nehmen
        }
    }
}
