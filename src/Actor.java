import javax.persistence.*;
import java.util.Date;

/**
 * Created by Julia on 30.10.2016.
 */
@Entity
@Table
public class Actor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int ActorId;

    private String firstName;
    private String lastName;
    private String sex;
    private Date birthDate;

    public Actor() {
    }

    public Actor(String firstName, String lastName, String sex, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    public int getActorId() {
        return ActorId;
    }

    public void setActorId(int actorId) {
        ActorId = actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "ActorId=" + ActorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
