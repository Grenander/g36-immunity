package se.lexicon.immunity.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.immunity.model.dto.*;
import se.lexicon.immunity.model.entity.*;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class DTOConverterImpl implements DTOConverterService{

    @Override
    public ContactInfoDTO toDTO(ContactInfo contactInfo) {
        ContactInfoDTO contactInfoDTO = null;
        if(contactInfo != null){
            contactInfoDTO = new ContactInfoDTO();
            contactInfoDTO.setId(contactInfo.getId());
            contactInfoDTO.setPhone(contactInfo.getPhone());
            contactInfoDTO.setEmail(contactInfo.getEmail());
        }
        return contactInfoDTO;
    }

    @Override
    public AddressDTO toDTO(Address address) {
        AddressDTO addressDTO = null;
        if(address != null){
            addressDTO = new AddressDTO();
            addressDTO.setId(address.getId());
            addressDTO.setStreet(address.getStreet());
            addressDTO.setZipCode(address.getZipCode());
            addressDTO.setCity(address.getCity());
        }
        return addressDTO;
    }

    @Override
    public PatientDTO toSmallDTO(Patient patient) {
        PatientDTO patientDTO = null;
        if(patient != null){
            patientDTO = new PatientDTO();
            patientDTO.setId(patient.getId());
            patientDTO.setPnr(patient.getPnr());
            patientDTO.setFirstName(patient.getFirstName());
            patientDTO.setLastName(patient.getLastName());
            patientDTO.setGender(patient.getGender());
            patientDTO.setBirthDate(patient.getBirthDate());
            patientDTO.setContactInfo(toDTO(patient.getContactInfo()));
        }
        return patientDTO;
    }

    @Override
    public PatientDTO toFullDTO(Patient patient, List<Booking> bookings) {
        PatientDTO patientDTO = null;
        if(patient != null){
            patientDTO = toSmallDTO(patient);
            patientDTO.setBookings(toSmallBookingDTOS(bookings));
        }
        return patientDTO;
    }

    @Override
    public BookingDTO toSmallDTO(Booking booking) {
        BookingDTO bookingDTO = null;
        if(booking != null){
            bookingDTO = new BookingDTO();
            bookingDTO.setId(booking.getId());
            bookingDTO.setDateTime(booking.getDateTime());
            bookingDTO.setPrice(booking.getPrice());
            bookingDTO.setAdministratorId(booking.getAdministratorId());
            bookingDTO.setVaccineType(booking.getVaccineType());
            bookingDTO.setVacant(booking.isVacant());
        }
        return bookingDTO;
    }

    @Override
    public BookingDTO toFullDTO(Booking booking) {
        BookingDTO bookingDTO = null;
        if(booking != null){
            bookingDTO = toSmallDTO(booking);
            bookingDTO.setPatient(toSmallDTO(booking.getPatient()));
            bookingDTO.setPremises(toSmallDTO(booking.getPremises()));
        }
        return bookingDTO;
    }

    @Override
    public PremisesDTO toSmallDTO(Premises premises) {
        PremisesDTO premisesDTO = null;
        if(premises != null){
            premisesDTO = new PremisesDTO();
            premisesDTO.setId(premises.getId());
            premisesDTO.setName(premises.getName());
            premisesDTO.setAddress(toDTO(premises.getAddress()));
        }
        return premisesDTO;
    }

    @Override
    public PremisesDTO toFullDTO(Premises premises, List<Booking> bookings) {
        PremisesDTO premisesDTO = null;
        if(premises != null){
            premisesDTO = toSmallDTO(premises);
            premisesDTO.setBookings(toSmallBookingDTOS(bookings));
        }
        return premisesDTO;
    }

    public List<BookingDTO> toSmallBookingDTOS(List<Booking> bookings){
        List<BookingDTO> bookingDTOS = new ArrayList<>();
        if(bookings != null){
            for(Booking booking : bookings){
                bookingDTOS.add(toSmallDTO(booking));
            }
        }
        return bookingDTOS;
    }
}
