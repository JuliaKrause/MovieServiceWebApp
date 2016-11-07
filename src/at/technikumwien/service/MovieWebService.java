package at.technikumwien.service;

import at.technikumwien.MovieList;
import at.technikumwien.entity.Movie;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by Julia on 28.10.2016.
 */
@WebService
public interface MovieWebService {

    @WebMethod
    MovieList getAllMovies();

    @WebMethod
	void importMovies(List<Movie> movieList);
	
	@WebMethod
    MovieList searchMoviesByTitle(String String);
}
