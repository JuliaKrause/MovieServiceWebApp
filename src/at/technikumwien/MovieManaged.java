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

    private List<Studio> myStudioList = new ArrayList<>();

    public List<Movie> searchMoviesByTitle() {
        return movieService.searchMoviesByTitle("first");
    }

    public List<Movie> getAllMovies() {
        //return movieService.getAllMovies();
        List<Actor> actorList = new ArrayList<>();
        actorList.add(new Actor("Pete", "Miller", null, new Date()));
        actorList.add(new Actor("Pete", "Blume", "male", new Date()));
        //actorList.add(new Actor("Heini", "Maier", "female", new Date()));

        movieService.addMovie(new Movie("jetztaber", "naja", 120, 2016, actorList, new Studio("Universal", "USA", null)));
        //List<Movie> movieList = new ArrayList<>();
        //movieList.add(new Movie("noch eins", "naja", 100, 1990, null, null));
        //movieList.add(new Movie("firstfirst", "naja", 100, 1990, null, null));
        //movieService.addMovies(movieList);
        return null;

    }

}
