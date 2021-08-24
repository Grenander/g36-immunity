package se.lexicon.immunity.model.entity;

import org.hibernate.annotations.GenericGenerator;
import se.lexicon.immunity.model.Gender;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Patient {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(unique = true)
    private String pnr;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
    @OneToOne(
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY
    )
    private ContactInfo contactInfo;

    public Patient(String id, String pnr, String firstName, String lastName, LocalDate birthDate, Gender gender) {
        this.id = id;
        this.pnr = pnr;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public Patient(String pnr, String firstName, String lastName, LocalDate birthDate, Gender gender) {
        this(null, pnr, firstName, lastName, birthDate, gender);
    }

    public Patient() {
    }

    public String getId() {
        return id;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(getId(), patient.getId()) && Objects.equals(getPnr(), patient.getPnr()) && Objects.equals(getFirstName(), patient.getFirstName()) && Objects.equals(getLastName(), patient.getLastName()) && Objects.equals(getBirthDate(), patient.getBirthDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPnr(), getFirstName(), getLastName(), getBirthDate());
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", pnr='" + pnr + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
