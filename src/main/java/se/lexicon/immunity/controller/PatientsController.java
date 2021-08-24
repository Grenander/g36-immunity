package se.lexicon.immunity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.lexicon.immunity.data.PatientDAO;
import se.lexicon.immunity.model.dto.PatientDTO;
import se.lexicon.immunity.model.entity.ContactInfo;
import se.lexicon.immunity.model.entity.Patient;

@RestController
public class PatientsController {

    private final PatientDAO patientDAO;

    public PatientsController(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @PostMapping("/api/v1/patients")
    public ResponseEntity<Patient> createPatient(@RequestBody PatientDTO patientDTO){
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

        return ResponseEntity.status(201).body(persisted);
    }

}
