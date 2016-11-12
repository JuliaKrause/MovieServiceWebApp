package at.technikumwien;

import at.technikumwien.entity.Movie;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by regula on 01.11.16.
 */
@XmlRootElement(name="movies")
@XmlAccessorType(XmlAccessType.FIELD)

public class MovieList {
	@XmlElementWrapper(name="movies")
	@XmlElement(name="movie")
	private List<Movie> MovieList;

	public MovieList() {
	}

	public MovieList(List<Movie> movieList) {
		MovieList = movieList;
	}

	@XmlElementWrapper(name="movies")
	@XmlElement(name="movie")
	public List<Movie> getMovieList() {
		return MovieList;
	}

	public void setMovieList(List<Movie> movieList) {
		MovieList = movieList;
	}

	@Override
	public String toString() {
		return "MovieList{" +
				"MovieList=" + MovieList +
				'}';
	}
}
