package at.technikumwien;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * Created by Julia on 30.10.2016.
 */
@Entity
@Table

@NamedQuery(name="Actor.selectAll", query="Select n FROM Actor n")

@XmlType(propOrder={"firstName", "lastName", "sex", "birthDate"})
public class Actor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int ActorId;

    @Basic
    private String firstName;

    @Basic
    @Column(nullable = false)
    private String lastName;

    @Basic
    private String sex;

    @Basic
    @Column(nullable = false)
    private Date birthDate;

    public Actor() {
    }

    public Actor(String firstName, String lastName, String sex, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthDate = birthDate;
    }

/*    public int getActorId() {
        return ActorId;
    }

    public void setActorId(int actorId) {
        ActorId = actorId;
    }*/
	@XmlAttribute(name="firstname")

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

	@XmlAttribute(name="lastname")   
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	@XmlAttribute(name="sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

	@XmlAttribute(name="birthdate")
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
