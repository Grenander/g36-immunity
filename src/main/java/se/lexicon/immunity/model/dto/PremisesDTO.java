package se.lexicon.immunity.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

public class PremisesDTO implements Serializable {

    private String id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AddressDTO address;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<BookingDTO> bookings;

    public PremisesDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public List<BookingDTO> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingDTO> bookings) {
        this.bookings = bookings;
    }
}
