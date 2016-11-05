package at.technikumwien.managed;

import at.technikumwien.service.MovieService;
import at.technikumwien.entity.Actor;
import at.technikumwien.entity.Movie;
import at.technikumwien.entity.Studio;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Julia on 05.11.2016.
 */
@RequestScoped
@Named("importMovie")
public class ImportMovieManaged {

    @Inject
    private MovieService movieService;

    public void importMovies() {
        List<Actor> actorList = new ArrayList<>();
        actorList.add(new Actor("Heini", "Maier", "female", new Date()));
        actorList.add(new Actor("Pete", "Miller", "male", new Date()));
        List<Actor> actorList2 = new ArrayList<>();
        actorList2.add(new Actor("Heini", "Maier", "male", new Date()));
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("FILM", "naja", 100, 1990, actorList, new Studio("Universal", "USA", "23456")));
        movieList.add(new Movie("FILM2", "naja", 100, 1990, actorList, new Studio("Universal", "USA", "23456")));

        movieService.importMovies(movieList);

    }

}
