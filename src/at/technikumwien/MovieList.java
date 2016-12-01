package at.technikumwien;

import at.technikumwien.entity.Movie;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by regula on 01.11.16.
 */

//so, jetzt geht's, also man kann seine movies.xml über soap ui importieren,
//wenn man die erste Zeile (xml version) weglässt
//und alles zwischen die ser:importMovies tags kopiert
//es gibt kein arg0 mehr - dafür musste man in MovieWebService schreiben:
//@WebMethod
//void importMovies(@WebParam(name="movies") MovieList movieList);
//ansonsten größte Änderung, die ich gemacht habe: in MovieWebService usw. statt List<Movie> MovieList als Parameter genommen
//vorher wurde diese Klasse hier gar nicht benutzt!
//weitere Änderungen: column description in Table Movie mit @Column(length = 500) (in Movie-Klasse) größer gemacht
//für alle Entities id typ von long auf Long geändert

//Übung 4: wsimport generieren von files für MovieClient ging, aber das Ergebnis funkt noch gar nicht
//Übung 5: geht gar nicht, weil beim Wildfly auf einmal WeldStartService fehlt oder sowas,
//komme gar nicht dahin, überhaupt was mit REST zu versuchen
//muss an irgendwelchen neuen dependencies liegen

@XmlAccessorType(XmlAccessType.FIELD)

public class MovieList {
    @XmlElement(name="movie")
    private List<Movie> movies;

    //Unmarshaller verlangt leeren ctor
    public MovieList() {
    }

    public MovieList(List<Movie> movieList) {
        movies = movieList;
    }

    public List<Movie> getMovieList() {
        return movies;
    }

    public void setMovieList(List<Movie> movieList) {
        movies = movieList;
    }

    @Override
    public String toString() {
        return "MovieList{" +
                "MovieList=" + movies +
                '}';
    }
}
