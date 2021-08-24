package se.lexicon.immunity.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.immunity.data.BookingDAO;
import se.lexicon.immunity.data.PatientDAO;
import se.lexicon.immunity.exception.AppResourceNotFoundException;
import se.lexicon.immunity.model.dto.PatientDTO;
import se.lexicon.immunity.model.entity.Booking;
import se.lexicon.immunity.model.entity.ContactInfo;
import se.lexicon.immunity.model.entity.Patient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService{

    private final PatientDAO patientDAO;
    private final BookingDAO bookingDAO;
    private final DTOConverterService converterService;

    public PatientServiceImpl(PatientDAO patientDAO, BookingDAO bookingDAO, DTOConverterService converterService) {
        this.patientDAO = patientDAO;
        this.bookingDAO = bookingDAO;
        this.converterService = converterService;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public PatientDTO create(PatientDTO patientDTO){
        Patient patient = new Patient(
                patientDTO.getPnr(),
                patientDTO.getFirstName(),
                patientDTO.getLastName(),
                patientDTO.getBirthDate(),
                patientDTO.getGender()
        );

        if(patientDTO.getContactInfo() != null){
            ContactInfo contactInfo = new ContactInfo(
                    patientDTO.getContactInfo().getEmail(),
                    patientDTO.getContactInfo().getPhone()
            );
            patient.setContactInfo(contactInfo);
        }

        Patient persisted = patientDAO.save(patient);

        return converterService.toFullDTO(persisted, null);
    }

    @Transactional(readOnly = true)
    public Patient internalFindById(String id){
        return patientDAO.findById(id)
                .orElseThrow(() -> new AppResourceNotFoundException("Could not find patient with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDTO> findAll(){
        return patientDAO.findAll().stream()
                .map(converterService::toSmallDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDTO findById(String id) {
        Patient patient = internalFindById(id);
        List<Booking> bookings = bookingDAO.findByPatientId(id);
        return converterService.toFullDTO(patient, bookings);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public PatientDTO update(String id, PatientDTO patientDTO) {
        Patient patient = internalFindById(id);

        patient.setPnr(patientDTO.getPnr());
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setBirthDate(patientDTO.getBirthDate());
        patient.setGender(patientDTO.getGender());
        patient.getContactInfo().setEmail(patientDTO.getContactInfo().getEmail());
        patient.getContactInfo().setPhone(patientDTO.getContactInfo().getPhone());

        patient = patientDAO.save(patient);
        return converterService.toFullDTO(patient, bookingDAO.findByPatientId(id));
    }
}
