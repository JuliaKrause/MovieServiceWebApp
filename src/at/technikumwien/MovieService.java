package at.technikumwien;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.*;
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
        /*int count = em.createQuery("SELECT n FROM Actor n WHERE n.firstName ='" + firstName
        + "' and n.lastName ='" + lastName + "' and n.sex ='" + sex + "'").getResultList().size();*/
        int count = em.createQuery("SELECT n FROM Actor n WHERE n.firstName ='" + firstName
                + "' and n.lastName ='" + lastName + "'").getResultList().size();

        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean existsStudio(Studio studio) {
        String name = studio.getName();
        String countryCode = studio.getCountryCode();
        String postCode = studio.getPostCode();
        int count = em.createQuery("SELECT n FROM Studio n WHERE n.name ='" + name
                + "' and n.countryCode ='" + countryCode + "' and n.postCode ='" + postCode + "'").getResultList().size();

        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    public int getActorIdFromDB (Actor actor) {
        String firstName = actor.getFirstName();
        String lastName = actor.getLastName();
        String sex = actor.getSex();
        Date birthDate = actor.getBirthDate();
        Actor actorFromDB = (Actor) em.createQuery("SELECT n FROM Actor n WHERE n.firstName ='" + firstName
                + "' and n.lastName ='" + lastName + "'").getSingleResult();
        int actorIdFromDB = actorFromDB.getActorId();
        return actorIdFromDB;
    }

    public int getStudioIdFromDB (Studio studio) {
        String name = studio.getName();
        String countryCode = studio.getCountryCode();
        String postCode = studio.getPostCode();

        Studio studioFromDB = (Studio) em.createQuery("SELECT n FROM Studio n WHERE n.name ='" + name
                + "' and n.countryCode ='" + countryCode + "' and n.postCode ='" + postCode + "'").getSingleResult();
        int studioIdFromDB = studioFromDB.getStudioId();
        return studioIdFromDB;
    }


    //Ein Film darf nur dann importiert werden, wenn alle zugehörigen Schauspieler
    //und das zugehörige Studio bereits in der Datenbank vorhanden sind
    //überprüfe schauspieler durch query und ersetze schauspieler-objekt durch geladenen (MANAGED!) schauspieler
    //leider weiss ich nicht, was das heisst (oder ob ich das überhaupt richtig gehört habe
    //aber ich interpretiere es so: für jeden tatsächlich vorhandenen Schauspieler hole ich mir aus der Datenbank die Id
    //und setze sie dann für diesen Schauspieler in der Schauspieler-Liste des Films
    //genauso für Studio
    //das scheint zu reichen, damit der entity manager das persist machen kann
    //die studio id erscheint als Fremdschluessel in der Movie Tabelle
    //die entsprechende Zeile wird in der movie_actor Tabelle hinzugefügt
    //dafuer musste ich in Actor und Studio den getter und setter fuer die Id wieder einkommentieren
    //und daher auch in der XMLType PropOrder hinzufuegen
    //bislang noch nicht geklaert ist:
    //warum schreibt er mir jeden Film doppelt in die db?
    //wenn mehrere Filme hinzugefuegt werden sollen und einer nicht geht, dann wird aber anscheinend denen,
    //die gehen wuerden, eine Id gegeben, denn beim naechsten erfolgreichen Hinzufuegen von Filmen
    //sieht man in der db, dass Ids uebersprungen wurden
    //wie kann ich eine Methode importMovies() in MovieManaged aufrufen?
    //ich glaube, ein Vergleich von null (in der Actor- oder Studio- Instanz) zu null
    // (in einer Spalte der actor- oder studio-Tabelle) geht nicht so einfach (probieren)

    public void importMovies(List<Movie> movieList) {
        System.out.println("**********************************************************");
        System.out.println(em.toString());
        System.out.println(em.getEntityManagerFactory());
        System.out.println("**********************************************************");
        for(Movie movie : movieList) {
        System.out.println("MOVIE IN IMPORT LIST " + movie.toString());
            for (Actor actor : movie.getActorList()) {
                if (!existsActor(actor)) {
                    throw new EJBException("can't find actor");
                } else {
                    int actorId = getActorIdFromDB(actor);
                    System.out.println("ACTOR ID IS: ");
                    System.out.println(actorId);
                    actor.setActorId(actorId);
                }
            }

            if (!existsStudio(movie.getStudio())) {
                throw new EJBException("can't find studio");
            } else {
                int studioId = getStudioIdFromDB(movie.getStudio());
                System.out.println("Studio ID IS: ");
                System.out.println(studioId);
                movie.getStudio().setStudioId(studioId);
            }

            em.persist(movie);
        }
    }
}
