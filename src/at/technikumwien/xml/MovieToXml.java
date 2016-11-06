package at.technikumwien.xml;

import at.technikumwien.entity.Actor;
import at.technikumwien.entity.Movie;
import at.technikumwien.entity.Studio;

import javax.xml.bind.*;
import java.io.File;
import java.util.*;

public class MovieToXml {
	public static void main(String[] args) throws JAXBException {
		//create Actors
		List<Actor> actors = new ArrayList<>();
		Actor actor1 = new Actor("Anna", "Apfelbaum", "f", new Date());
		actors.add(actor1);
		Actor actor2 = new Actor("Bernie", "Beaver", "m", new Date());
		actors.add(actor2);
		//create studio
		Studio studio = new Studio("Studio1", "at", "1090");
		//create movie
		Movie movie = new Movie("Movie1", "long description or short description", 123, 2015, actors, studio);

		JAXBContext jaxbContext = JAXBContext.newInstance(Movie.class);
		Marshaller marshaller = jaxbContext.createMarshaller();

		marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		marshaller.marshal(movie, System.out);
		marshaller.marshal(movie, new File("xml/movie.xml"));
	}
}
