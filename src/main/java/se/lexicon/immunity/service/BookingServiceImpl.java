package se.lexicon.immunity.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.immunity.data.BookingDAO;
import se.lexicon.immunity.data.PatientDAO;
import se.lexicon.immunity.data.PremisesDAO;
import se.lexicon.immunity.exception.AppResourceNotFoundException;
import se.lexicon.immunity.model.dto.BookingDTO;
import se.lexicon.immunity.model.entity.Booking;
import se.lexicon.immunity.model.entity.Patient;
import se.lexicon.immunity.model.entity.Premises;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingDAO bookingDAO;
    private final PremisesDAO premisesDAO;
    private final PatientDAO patientDAO;
    private final DTOConverterService converterService;

    public BookingServiceImpl(BookingDAO bookingDAO, PremisesDAO premisesDAO, PatientDAO patientDAO, DTOConverterService converterService) {
        this.bookingDAO = bookingDAO;
        this.premisesDAO = premisesDAO;
        this.patientDAO = patientDAO;
        this.converterService = converterService;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public BookingDTO create(BookingDTO bookingDTO, String premisesId) {
        Booking booking = new Booking(
                bookingDTO.getDateTime(),
                bookingDTO.getPrice(),
                bookingDTO.getVaccineType()
        );

        Premises premises = premisesDAO.findById(premisesId)
                .orElseThrow(() -> new AppResourceNotFoundException("Could not find premise id " + premisesId));

        booking.setPremises(premises);

        Booking persisted = bookingDAO.save(booking);

        return converterService.toFullDTO(persisted);
    }

    @Transactional(readOnly = true)
    public Booking internalFindById(String id){
        return bookingDAO.findById(id)
                .orElseThrow(() -> new AppResourceNotFoundException("Could not find booking with id " + id));
    }

    @Override
    public BookingDTO findById(String id) {
        return converterService.toFullDTO(internalFindById(id));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public BookingDTO book(String id, String patientId) {
        Booking booking = bookingDAO.findById(id).orElseThrow(() -> new AppResourceNotFoundException("Could not find booking with id " + id));
        Patient patient = patientDAO.findById(patientId).orElseThrow(() -> new AppResourceNotFoundException("Could not find patient with id " + id));

        booking.setPatient(patient);
        booking.setVacant(false);

        Booking persisted = bookingDAO.save(booking);

        return converterService.toFullDTO(persisted);
    }
}
