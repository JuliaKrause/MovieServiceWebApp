package at.technikumwien;

import javax.xml.bind.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

/**
 * Created by regula on 01.11.16.
 */
public class XmlToMovieList {
	public static void main(String[] args) throws JAXBException, FileNotFoundException {
		JAXBContext jaxbContext = JAXBContext.newInstance(MovieList.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		Source source = new StreamSource(new File("xml/movies.xml"));
		JAXBElement<MovieList> jaxbElement = unmarshaller.unmarshal(source, MovieList.class);
		MovieList movieList = jaxbElement.getValue();

		System.out.print(movieList.getMovieList());

	}


}
