package at.technikumwien.xml;

import at.technikumwien.MovieList;
import at.technikumwien.entity.Movie;

import javax.xml.bind.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.List;

import static org.richfaces.component.AutocompleteLayout.list;

/**
 * Created by regula on 01.11.16.
 */
public class XmlConverter {
	JAXBContext jaxbContext = JAXBContext.newInstance(MovieList.class);
	Marshaller marshaller = jaxbContext.createMarshaller();
	Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

	public XmlConverter() throws JAXBException {
	}

	public MovieList convertToMovieList(String xml) {
		InputStream soapInput = new ByteArrayInputStream(xml.getBytes());
		Source source = new StreamSource(soapInput);
		JAXBElement<MovieList> jaxbElement = null;
		try {
			jaxbElement = unmarshaller.unmarshal(source, MovieList.class);//this is the line where the problem is
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		System.out.println("jaxbElement: ");
		System.out.println(jaxbElement);//jaxbElement is null
		System.out.println("jaxbElementValue: ");
		System.out.println(jaxbElement.getValue());//geht dann natuerlich nicht
		//das Ganze hat nichts damit zu tun, ob die anderen files im src > xml folder vorhanden sind oder nicht
		MovieList movieList = jaxbElement.getValue();
		return movieList;
	}

	public String convertToXml(MovieList movieList) {
		try {
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		} catch (PropertyException e) {
			e.printStackTrace();
		}


		StringWriter sw = new StringWriter();
		try {
			marshaller.marshal(list, sw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}



}
