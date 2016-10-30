import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Julia on 30.10.2016.
 */
@Entity
@Table
@XmlRootElement

@NamedQuery(name="Movie.selectAll", query="Select n FROM Movie n")

public class Movie {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int movieId;

    private String title;
    private String description;
    private int length;
    private int releaseYear;

    @ManyToMany
    private List<Actor> actorList;

    @ManyToOne
    private Studio studio;

    public Movie() {
    }

    public Movie(String title, String description, int length, int releaseYear, List<Actor> actorList, Studio studio) {
        this.title = title;
        this.description = description;
        this.length = length;
        this.releaseYear = releaseYear;
        this.actorList = actorList;
        this.studio = studio;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", length=" + length +
                ", releaseYear=" + releaseYear +
                ", actorList=" + actorList +
                ", studio=" + studio +
                '}';
    }
}
