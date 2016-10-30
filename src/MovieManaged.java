import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
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
    }

}
