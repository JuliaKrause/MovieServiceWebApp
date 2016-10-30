import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Julia on 30.10.2016.
 */
@Stateless
public class MovieService {

    @PersistenceContext
    private EntityManager em;

    public List<Movie> getAllMovies() {
        return em.createNamedQuery("Movie.selectAll", Movie.class).getResultList();

    }


}
