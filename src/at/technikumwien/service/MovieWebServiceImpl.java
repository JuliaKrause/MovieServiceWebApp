package at.technikumwien.service;

import at.technikumwien.MovieList;
import at.technikumwien.entity.Movie;
import at.technikumwien.xml.XmlConverter;

import javax.inject.Inject;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import java.util.List;

/**
 * Created by Julia on 28.10.2016.
 */

 
 // hier kommen die requests an. hier will ich parsen etc, und dann das service aufrufen. das schreibt dann das zeugs
// in die db oder holts raus oder whatever.
 
@WebService(endpointInterface= "at.technikumwien.service.MovieWebService",
        serviceName="MovieWebService",
        portName="MovieWebServicePort")
public class MovieWebServiceImpl implements MovieWebService {

    @Inject
    private MovieService service;

    @Override
    public MovieList getAllMovies() {

        return new MovieList(service.getAllMovies());

    }

    @Override
    public void importMovies(MovieList movieList) {

	/*XmlConverter converter = null;
		try {
			converter = new XmlConverter();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		MovieList movielist = converter.convertToMovieList(xml);*/
        //service.importMovies(movielist.getMovieList());
        service.importMovies(movieList);
	}

    @Override
    public MovieList searchMoviesByTitle(String string) {
		MovieList movieList = new MovieList(service.searchMoviesByTitle(string));
		return movieList;
    }
    
}
