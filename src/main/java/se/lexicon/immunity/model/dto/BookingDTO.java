package se.lexicon.immunity.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingDTO implements Serializable {
    private String id;
    private LocalDateTime dateTime;
    private BigDecimal price;
    private String administratorId;
    private String vaccineType;
    private boolean vacant;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PremisesDTO premises;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PatientDTO patient;

    public BookingDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(String administratorId) {
        this.administratorId = administratorId;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    public boolean isVacant() {
        return vacant;
    }

    public void setVacant(boolean vacant) {
        this.vacant = vacant;
    }

    public PremisesDTO getPremises() {
        return premises;
    }

    public void setPremises(PremisesDTO premises) {
        this.premises = premises;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }
}
