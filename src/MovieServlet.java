import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Julia on 30.10.2016.
 */

@WebServlet("/movies")
public class MovieServlet extends HttpServlet {

    @Inject
    private MovieService movieService;

    public MovieServlet() {
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<body>");

        movieService.getAllMovies().forEach(movie -> {
            out.println("<h1>" + movie.getTitle() + "</h1>");
            out.println("<p>" + movie.getDescription() + "</p>");
        });
    }
}