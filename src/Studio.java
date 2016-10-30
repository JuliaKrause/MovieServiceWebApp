import javax.persistence.*;

/**
 * Created by Julia on 30.10.2016.
 */

@Entity
@Table
public class Studio {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int studioId;

    private String name;
    private String countryCode;
    private String postCode;

    public Studio() {
    }

    public Studio(String name, String countryCode, String postCode) {
        this.name = name;
        this.countryCode = countryCode;
        this.postCode = postCode;
    }

    public int getStudioId() {
        return studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

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
