package se.lexicon.immunity.service;

import org.springframework.transaction.annotation.Transactional;
import se.lexicon.immunity.model.dto.BookingDTO;
import se.lexicon.immunity.model.dto.PatientDTO;

import java.util.List;

public interface BookingService {
    @Transactional(rollbackFor = RuntimeException.class)
    BookingDTO create(BookingDTO bookingDTO, String premisesId);

    BookingDTO findById(String id);

    BookingDTO book(String id, String patientId);
}