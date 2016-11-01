package at.technikumwien;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by Julia on 28.10.2016.
 */
@WebService
public interface MovieWebService {

    @WebMethod
    List<Movie> getAllMovies();

    @WebMethod
    void searchMoviesByTitle();
}
