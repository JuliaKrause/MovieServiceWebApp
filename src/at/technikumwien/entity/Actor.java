package at.technikumwien.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

import java.util.Date;

/**
 * Created by Julia on 30.10.2016.
 */
@Entity
@Table

@NamedQueries({ @NamedQuery(name="Actor.selectAll", query="SELECT a FROM Actor a"),
        @NamedQuery(name="Actor.select", query="Select a FROM Actor a WHERE a.firstName = :firstName and a.lastName = :lastName and a.sex = :sex and a.birthDate = :birthDate")})

@XmlType(propOrder={"firstName", "lastName", "sex", "birthDate"})
public class Actor {

    //does actor also have to be an xml root element? I don't think so
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long actorId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String sex;

    @Column(nullable = false)
    private Date birthDate;

    public Actor() {
    }

    public Actor(Long actorId, String firstName, String lastName, String sex, Date birthDate) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    public Actor(String firstName, String lastName, String sex, Date birthDate) {
        this(null, firstName, lastName, sex, birthDate);
    }

    @XmlTransient
    public Long getActorId() {

        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

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

                "actorId=" + actorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
