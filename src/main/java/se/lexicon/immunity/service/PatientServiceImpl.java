package se.lexicon.immunity.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.immunity.data.PatientDAO;
import se.lexicon.immunity.exception.AppResourceNotFoundException;
import se.lexicon.immunity.model.dto.ContactInfoDTO;
import se.lexicon.immunity.model.dto.PatientDTO;
import se.lexicon.immunity.model.entity.ContactInfo;
import se.lexicon.immunity.model.entity.Patient;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{

    private final PatientDAO patientDAO;

    public PatientServiceImpl(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @Transactional(readOnly = true)
    public PatientDTO toDTO(Patient patient){
        PatientDTO patientDTO = null;
        if(patient != null){
            patientDTO = new PatientDTO();
            patientDTO.setId(patient.getId());
            patientDTO.setFirstName(patient.getFirstName());
            patientDTO.setLastName(patient.getLastName());
            patientDTO.setBirthDate(patient.getBirthDate());
            patientDTO.setPnr(patient.getPnr());
            patientDTO.setGender(patient.getGender());
            ContactInfoDTO contactInfoDTO = null;
            if(patient.getContactInfo() != null){
                contactInfoDTO = new ContactInfoDTO();
                contactInfoDTO.setId(patient.getContactInfo().getId());
                contactInfoDTO.setEmail(patient.getContactInfo().getEmail());
                contactInfoDTO.setPhone(patient.getContactInfo().getPhone());
                patientDTO.setContactInfo(contactInfoDTO);
            }
        }
        return patientDTO;
    }

    @Transactional(readOnly = true)
    public List<PatientDTO> toDTOs(List<Patient> patients){
        List<PatientDTO> patientDTOS = new ArrayList<>();
        if(patients != null){
            for(Patient patient : patients){
                patientDTOS.add(toDTO(patient));
            }
        }
        return patientDTOS;
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

        return toDTO(persisted);
    }

    @Transactional(readOnly = true)
    public Patient internalFindById(String id){
        return patientDAO.findById(id)
                .orElseThrow(() -> new AppResourceNotFoundException("Could not find patient with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDTO> findAll(){
        return toDTOs(patientDAO.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDTO findById(String id) {
        return toDTO(internalFindById(id));
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
        return toDTO(patient);
    }
}
