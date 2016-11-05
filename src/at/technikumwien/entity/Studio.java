package at.technikumwien.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.*;



/**
 * Created by Julia on 30.10.2016.
 */

@Entity


@NamedQuery(name="Studio.selectAll", query="Select n FROM Studio n")
@XmlType(propOrder = {"name", "countryCode", "postCode"})
public class Studio {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long studioId;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
	private String countryCode;

    @Column(nullable=false)
	private String postCode;

    public Studio() {
    }

    public Studio(String name, String countryCode, String postCode) {
        this.name = name;
        this.countryCode = countryCode;
        this.postCode = postCode;
    }

	@XmlTransient
    public long getStudioId() {
        return studioId;
    }

    public void setStudioId(long studioId) {
        this.studioId = studioId;
    }

	@XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@XmlAttribute(name="countrycode")
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

	@XmlAttribute(name="postcode")
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Override
    public String toString() {
        return "Studio{" +
                "studioId=" + studioId +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }
}
