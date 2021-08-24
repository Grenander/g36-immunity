package se.lexicon.immunity.service;

import se.lexicon.immunity.model.dto.*;
import se.lexicon.immunity.model.entity.*;

import java.util.List;

public interface DTOConverterService {

    ContactInfoDTO toDTO(ContactInfo contactInfo);
    AddressDTO toDTO(Address address);
    PatientDTO toSmallDTO(Patient patient);
    PatientDTO toFullDTO(Patient patient, List<Booking> bookings);
    BookingDTO toSmallDTO(Booking booking);
    BookingDTO toFullDTO(Booking booking);
    PremisesDTO toSmallDTO(Premises premises);
    PremisesDTO toFullDTO(Premises premises, List<Booking> bookings);

}
