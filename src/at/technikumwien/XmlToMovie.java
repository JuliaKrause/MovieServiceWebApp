package at.technikumwien;

import javax.xml.bind.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XmlToMovie {
	public static void main(String[] args) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Movie.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		Source source = new StreamSource(new File("xml/movie.xml"));
		JAXBElement<Movie> jaxbElement = unmarshaller.unmarshal(source, Movie.class);
		Movie movie = jaxbElement.getValue();

		System.out.println(movie);
	}

}

