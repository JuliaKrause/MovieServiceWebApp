package at.technikumwien.xml;

import at.technikumwien.MovieList;
import at.technikumwien.entity.Actor;
import at.technikumwien.entity.Movie;
import at.technikumwien.entity.Studio;

import javax.xml.bind.*;
import java.io.*;
import java.util.*;

/**
 * Created by regula on 01.11.16.
 */
public class MovieListToXml {
	public static void main(String[] args) throws JAXBException, IOException {
		//create Actors
		List<Actor> actors = new ArrayList<>();
		Actor actor1 = new Actor("Anna", "Apfelbaum", "f", new Date());
		Actor actor2 = new Actor("Bernie", "Beaver", "m", new Date());
		actors.add(actor1);
		actors.add(actor2);
		//create studio
		Studio studio = new Studio("Studio1", "at", "1090");
		//create movies
		Vector<Movie> movies = new Vector<>();
		Movie movie1 = new Movie("Movie1", "long description or short description", 123, 2015, actors, studio);
		Movie movie2 = new Movie("Movie2", "shorter description", 321, 2008, actors, studio);
		movies.add(movie1);
		movies.add(movie2);
		MovieList list = new MovieList(movies);

		JAXBContext jaxbContext = JAXBContext.newInstance(MovieList.class);
		Marshaller marshaller = jaxbContext.createMarshaller();

		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		StringWriter sw = new StringWriter();
		File file = new File("xml/movies.xml");
		FileWriter fw = new FileWriter(file);
		marshaller.marshal(list, fw);
		marshaller.marshal(list, sw);
		System.out.print(sw.toString());
		/*for(Movie movie : movies) {
			marshaller.marshal(movie, System.out);
			marshaller.marshal(movie, file);
		}*/
	}
}
