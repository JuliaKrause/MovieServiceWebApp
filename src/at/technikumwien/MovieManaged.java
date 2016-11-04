package at.technikumwien;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Julia on 30.10.2016.
 */

@RequestScoped
@Named("reqMovie")
public class MovieManaged {
    @Inject
    private MovieService movieService;

    //koennte gerne diese Methode aufrufen (sowie auch eine importMovies())
    public List<Movie> searchMoviesByTitle() {
        return movieService.searchMoviesByTitle("oll");
    }

    public List<Movie> getAllMovies() {
        //return movieService.getAllMovies();
        return movieService.searchMoviesByTitle("oll");

        //import Methode:
        /*List<Actor> actorList = new ArrayList<>();
        actorList.add(new Actor("Heini", "Maier", "female", new Date()));
        actorList.add(new Actor("Pete", "Miller", "male", new Date()));
        List<Actor> actorList2 = new ArrayList<>();
        actorList2.add(new Actor("Heini", "Maier", "male", new Date()));
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("FILM", "naja", 100, 1990, actorList, new Studio("Universal", "USA", "23456")));
        movieList.add(new Movie("FILM2", "naja", 100, 1990, actorList, new Studio("Universal", "USA", "23456")));
        movieService.importMovies(movieList);
        return null;*/
    }

}
