package at.technikumwien.managed;

import at.technikumwien.entity.*;
import at.technikumwien.service.MovieService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;
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


    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
        //return movieService.searchMoviesByTitle("1");

        /*int year = 1973;
        int month = 6;
        int day = 14;

        String date = year + "/" + month + "/" + day;
        java.util.Date utilDate = null;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            utilDate = formatter.parse(date);
            System.out.println("utilDate:" + utilDate);
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }

        List<Actor> actorList = new ArrayList<>();
        actorList.add(new Actor("Anna", "Apfelbaum", "f", utilDate));
        List<Actor> actorList2 = new ArrayList<>();
        actorList2.add(new Actor("Billy", "Byrd", "m", new Date()));
        //actorList2.add(new Actor("Heini", "Maier", "male", new Date()));
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("MovieC", "huh", 100, 1990, actorList, new Studio("Studio1", "at", "1234")));
        movieList.add(new Movie("MovieD", "huh", 100, 1990, actorList, new Studio("Studio1", "at", "1234")));

        movieService.importMovies(movieList);

        return null;*/
    }

}
