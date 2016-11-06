package at.technikumwien.managed;

import at.technikumwien.service.MovieService;
import at.technikumwien.entity.Movie;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by regula on 04.11.16.
 */
@RequestScoped
@Named("findMovie")
public class FindMovieManaged {

	@Inject
	private MovieService movieService;

	public List<Movie> getMoviesByTitle() {
		return movieService.searchMoviesByTitle("1");
	}

}
