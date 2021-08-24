package se.lexicon.immunity.model.dto;

import se.lexicon.immunity.model.demo.Gender;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDate;

public class PatientDTO implements Serializable {

    private String id;
    private String pnr;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
    private ContactInfoDTO contactInfo;


    public PatientDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public ContactInfoDTO getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfoDTO contactInfo) {
        this.contactInfo = contactInfo;
    }
}
