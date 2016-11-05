package at.technikumwien.service;

import at.technikumwien.entity.*;



import javax.ejb.EJBException;
import javax.ejb.Stateless;
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
	private ActorService as = new ActorService();
	private StudioService ss = new StudioService();

    public List<Movie> getAllMovies() {
        System.out.println("**********************************************************");
        System.out.println(em.toString());
        System.out.println(em.getEntityManagerFactory());
        System.out.println("**********************************************************");
        return em.createNamedQuery("Movie.selectAll", Movie.class).getResultList();

    }

    public List<Movie> searchMoviesByTitle(String searchString) {
        return em.createQuery("SELECT n FROM Movie n WHERE n.title LIKE '%" + searchString + "%'").getResultList();

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
        System.out.println("**********************************************************");
        System.out.println(em.toString());
        System.out.println(em.getEntityManagerFactory());
        System.out.println("**********************************************************");
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

    //funkt so, aber nicht, wenn ich die Methoden in ActorService und StudioServie aufrufe
    //warum schreibt er mir jeden Film doppelt in die db? (naja, es gibt task 1 und task 3, warum auch immer)
    //wenn mehrere Filme hinzugefuegt werden sollen und einer nicht geht, dann wird aber anscheinend denen,
    //die gehen wuerden, eine Id gegeben, denn beim naechsten erfolgreichen Hinzufuegen von Filmen
    //sieht man in der db, dass Ids uebersprungen wurden
    //wie kann ich eine Methode importMovies() in MovieManaged aufrufen? (verstehe ich immer noch nicht)
    public void importMovies(List<Movie> movieList) {
    	for(Movie movie : movieList) {
            System.out.println("MOVIE IN IMPORT LIST " + movie.toString());
            checkActors(movie.getActorList());
            checkStudio(movie.getStudio());

            //as.checkActors(movie.getActorList());
			//ss.checkStudio(movie.getStudio());

			System.out.println("importing movie " + movie.getTitle() + " in MovieService");
			em.persist(movie);

		}
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

}