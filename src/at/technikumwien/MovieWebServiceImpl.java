package at.technikumwien;

import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by Julia on 28.10.2016.
 */

@WebService(endpointInterface="at.technikumwien.MovieWebService",
        serviceName="MovieWebService",
        portName="MovieWebServicePort")


public class MovieWebServiceImpl implements MovieWebService {

    @Inject
    private MovieService service;

    @Override
    public List<Movie> getAllMovies() {

        return service.getAllMovies();

    }

    @Override
    public void searchMoviesByTitle() {
        service.searchMoviesByTitle("first");
    }


}
