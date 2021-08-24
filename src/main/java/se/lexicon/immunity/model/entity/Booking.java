package se.lexicon.immunity.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Booking {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private LocalDateTime dateTime;
    private BigDecimal price;
    private String administratorId;
    private String vaccineType;
    private boolean vacant;
    @ManyToOne(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "patient_id", table = "booking")
    private Patient patient;
    @ManyToOne(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "premises_id", table = "booking")
    private Premises premises;

    public Booking(String id, LocalDateTime dateTime, BigDecimal price, String administratorId, String vaccineType, boolean vacant) {
        this.id = id;
        this.dateTime = dateTime;
        this.price = price;
        this.administratorId = administratorId;
        this.vaccineType = vaccineType;
        this.vacant = vacant;
    }

    public Booking(LocalDateTime dateTime, BigDecimal price, String vaccineType) {
        this(null, dateTime, price, null, vaccineType, true);
    }

    public Booking() {
    }

    public String getId() {
        return id;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Premises getPremises() {
        return premises;
    }

    public void setPremises(Premises premises) {
        this.premises = premises;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return isVacant() == booking.isVacant() && Objects.equals(getId(), booking.getId()) && Objects.equals(getDateTime(), booking.getDateTime()) && Objects.equals(getPrice(), booking.getPrice()) && Objects.equals(getAdministratorId(), booking.getAdministratorId()) && Objects.equals(getVaccineType(), booking.getVaccineType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDateTime(), getPrice(), getAdministratorId(), getVaccineType(), isVacant());
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", dateTime=" + dateTime +
                ", price=" + price +
                ", administratorId='" + administratorId + '\'' +
                ", vaccineType='" + vaccineType + '\'' +
                ", vacant=" + vacant +
                '}';
    }
}
