package at.technikumwien.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

/**
 * Created by Julia on 30.10.2016.
 */
@Entity

@NamedQueries({ @NamedQuery(name="Movie.selectAll", query="SELECT n FROM Movie n"),
        @NamedQuery(name="Movie.findByTitleMov", query="SELECT n FROM Movie n WHERE n.title LIKE '%Mov%'")})


@XmlAccessorType(XmlAccessType.FIELD)
//@XmlRootElement(name="movie")
@XmlType(propOrder={"title", "description", "genre", "length", "releaseYear", "studio", "actorList"})

public class Movie {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @XmlTransient
    private Long movieId;

    @Basic
    @Column(nullable = false)
    @XmlAttribute
    private String title;

    @Basic
    @Column(length = 500)
    @XmlAttribute
    private String description;

    @Basic
    @XmlAttribute
    private String genre;

    @Basic
    @XmlAttribute
    private Integer length;

    @Basic
    @Column(nullable = false)
    @XmlAttribute(name="releaseyear")
    private int releaseYear;

    //may also need "mapped by"
    @ManyToMany(targetEntity=Actor.class, fetch=EAGER)
    @Column(nullable = false)
    @XmlElementWrapper(name="actors")
    @XmlElement(name="actor")
    private List<Actor> actorList;

    @ManyToOne

    @XmlElement
    private Studio studio;

    public Movie() {
    }

    public Movie(String title, String description, String genre, int length, int releaseYear, Studio studio, List<Actor> actorList) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.length = length;
        this.releaseYear = releaseYear;
        this.actorList = actorList;
        this.studio = studio;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
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

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
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

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", length=" + length +
                ", releaseYear=" + releaseYear +
                ", actorList=" + actorList +
                ", studio=" + studio +
                '}';
    }
}
