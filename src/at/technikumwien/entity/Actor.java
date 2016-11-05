package at.technikumwien.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.*;


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
    private long actorId;

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

    public Actor(String firstName, String lastName, String sex, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthDate = birthDate;
    }

	@XmlTransient
    public long getActorId() {
        
		return actorId;
	}

    public void setActorId(long actorId) {
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
